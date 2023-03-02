# BUG Record

```
BUG Record From 2022-02-15 23:06
```

# Mybatis Bug
* **Unknown element <" + nodeName + "> in SQL statement.**    
### Bug Occur Cause
1. 使用了@Select标签
2. Selection标签中SQL语句使用了`<script></script>`
3. 在script标签中使用了动态标签`<WHERE></WHERE>`

### Bug Occur SQL
```java
class OccurTest {
    
    @Select("<script> SELECT\n" +
            "art.article_id AS articleId,\n" +
            "art.article_title AS articleTitle,\n" +
            "art.article_desc AS articleDesc,\n" +
            "ui.nickname AS author,\n" +
            "art.create_time AS createTime\n" +
            "FROM\n" +
            "article AS art\n" +
            "LEFT JOIN user_info AS ui ON art.user_id = ui.user_id\n" +
            "<WHERE> \n" +
            "<if test='articleId != null'> \n" +
            " art.articleId = #{articleId}\n" +
            "</if>\n" +
            "</WHERE>\n" +
            "</script>")
    public void occurBugFunction() {
        
    }
}
```
### Bug fixed Scheme
1. 修改动态SQL`<WHERE></WHERE>`标签为`<where></where>`

### Bug fixed Cause
1. <strong>org.apache.ibatis.scripting.xmltags.XMLScriptBuilder#initNodeHandlerMap()</strong>函数中定义了以注解形式Script标签中所支持的动态tag,**如where if when**等等
2. 在<strong>org.apache.ibatis.scripting.xmltags.XMLScriptBuilder#parseDynamicTags(XNode)</strong>函数中,对于在注解script标签中书写的动态标签是否支持的判断,是通过动态标签作为key,在this.nodeHandlerMap.get(nodeName)语句获取,所以动态标签必须与其nodeHandlerMap中key键一致,否则就会不识别从而抛出异常

----

# Thymeleaf Bug
* **Error resolving template template might not exist or might not be accessible**

### Bug Occur Cause
1. 打包配置项中未排除html,导致打包后templates文件夹丢失,导致thymeleaf无法获取模板文件

### Bug fixed Scheme
1. 在pom文件打包配置中,将src/main/resources下的html排除
```javascript
                    <include>**/*.html</include>

<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
            <configuration>
                <mainClass>com.twbat.blog</mainClass>
                <classifier>execute</classifier>
            </configuration>
        </plugin>
    </plugins>

    <resources>
        <resource>
            <directory>src/main/resources</directory>
            <includes>
                <include>**/*.yml</include>
                <include>**/*.properties</include>
                <include>**/*.xml</include>
                ----
                <include>**/*.html</include>
                ----
            </includes>
            <filtering>false</filtering>
        </resource>
    </resources>
</build>
```
* 解决思路,
* 1: 首先是target文件夹中未发现template文件 
* 2: 通过debug Thymeleaf源码发现最终调用classLoader.getResource(templatePath)结果为空
* 3: 通过测试类测试 (this.getClassLoader.getResource(templatePath))是否为null, 测试mybatis mapper.xml文件同路径进行比对
```java
/**
 * ...省略注解
 */
class Test {

    /**
     * ... 省略注解
     */
    void test() {
        URL resource = this.getClass().getClassLoader().getResource("templates/EmailTemplate.html");
        URL resource2 = this.getClass().getClassLoader().getResource("mapper/article/ArticleTagMapper.xml");
        assert resource == null;
        assert resource2 == null;
        /*
                经测试
                resource2可以正常获取到路径
                但是resource不可以
                所以可以确定是因为打包配置导致templates没有被排除
         */
    }
}
```

# Activity6 And Mybatis Plus Bug
### Bug Occur Cause
1. Activity6中自带Mybatis版本,同时Mybatis Plus SpringBoot Starter中也同时提供了Mybatis版本,两者引发冲突所以导致项目启动失败

### Bug fixed Scheme
1. 
```javascript
        <dependency>
            <groupId>org.activiti</groupId>
            <artifactId>activiti-spring-boot-starter-basic</artifactId>
            <version>6.0.0</version>
    ------
            <exclusions>
                <exclusion>
                    <artifactId>mybatis</artifactId>
                    <groupId>org.mybatis</groupId>
                </exclusion>
            </exclusions>
    ------
        </dependency>
```

# Integer equals function bug
### Bug Occur Cause 
1. 参数为前端传递，数值类型。 后端函数由Object类型接收 (Object接收是因为函数功能所需,无法确定指定类型)
2. Integer类型变量.equals(Object前端传递数值参数) Integer 类型变量值为1 ,Object 类型变量值为 "1"

### Bug Occur Code
```java
public class Test {
    public void occurFunction(Object args) {
        // assert args not null 
        // ...
        // args = "1"(String) JwtUtil.getUserId() = 1 (Integer)
        if (args.equals(JwtUtil.getUserId())) {
            // ...
            return;
        }
    }
}
```

### Bug Fixed Scheme
1. 首先查阅了String类的equals函数和Integer类的equals函数
2. 不同类型即便值一样也无法相等
3. fastJson转换前端参数时将传入的数值1转换成String,导致equals比较双方非同一类型
4. 解决方案如下 :
```java
public class Test {
    public void occurFunction(Object args) {
        // assert args not null 
        // ...
        // args = "1"(String) JwtUtil.getUserId() = 1 (Integer)
        if (Integer.valueOf(args.toString()).equals(JwtUtil.getUserId())) {
            // ...
            return;
        }
        
        // or 
        
        if (((Integer) arg).equals(JwtUtil.getUserId())) {
            // ...
            return;
        }
    }
}
```