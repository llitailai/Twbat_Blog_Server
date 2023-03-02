package cn.twbat.web.api;

import cn.twbat.web.api.business.domain.permission.annotation.ModuleInfo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022-0217 - 10:49
 * @desciption Web端启动类
 */
@SpringBootApplication(scanBasePackages = {"com.twbat", "com.twbat.blog", "cn.twbat", "com.twbat.blog.common.web", "com.twbat.blog.common.web.config", "com.twbat.blog.common.web.aop","com.twbat.blog.common.util.util.email","com.twbat.blog.common.util.util.spring"})
@MapperScan({"com.twbat.blog.center.permission.repository", "com.twbat.blog.center.permission.repository.*.mapper.*", "cn.twbat.web.api.business.repository.*"})
@EnableFeignClients
@ModuleInfo(name = "web_api",packagePath = "cn.twbat.web.api")
public class WebApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApiApplication.class, args);
    }

}
