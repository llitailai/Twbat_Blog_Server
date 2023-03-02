package com.twbat.blog.system;

import cn.twbat.logger.core.factory.LoggerFactory;
import cn.twbat.logger.core.log.Logger;
import com.twbat.blog.common.util.util.DateUtils;
import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author darkltl
 * @className BlogSystemApplication
 * @email darkltl@163.com
 * @date 2021/7/22 - 13:49
 * @description 博客后台系统启动类
 */
@SpringBootApplication(scanBasePackages = {"com.twbat", "com.twbat.blog", "cn.twbat", "com.twbat.blog.common.web", "com.twbat.blog.common.web.config", "com.twbat.blog.common.web.aop","com.twbat.blog.common.util.util.email"},exclude = SecurityAutoConfiguration.class)
@MapperScan({"com.twbat.blog.system.business.repository.*", "com.twbat.blog.center.permission.repository", "com.twbat.blog.center.permission.repository.*.mapper.*"})
public class BlogSystemApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(BlogSystemApplication.class);

    public static void main(String[] args) {
        LOGGER.info("blog_system start , start time : {}", DateUtils.getTime());
        SpringApplication.run(BlogSystemApplication.class, args);
        LOGGER.info("blog_system start , start endTime: {}", DateUtils.getTime());
    }
}
