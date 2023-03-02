package cn.twbat.web.api.business.domain.permission.annotation;

import java.lang.annotation.*;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/18 - 11:21
 * @desciption
 * 模块信息注解
 * 用于标注模块名称,等其他配置项
 * 请将其应用到SpringBootApplication上
 * 除此之外并不生效
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
public @interface ModuleInfo {

    /**
     * 模块名称,对应路径
     * 如D:\File\Code\Project\Twbat\Java\blog\web_api
     * Java 模块名称为 web_api
     * 如果路径与模块名称不符,以路径名称为基准
     * @return 模块名称
     */
    String name() default "";


    /**
     * 模块包名配置项
     * cn.twbat.web.api
     */
    String packagePath() default  "";

}
