package com.twbat.blog.common.database.redis.config;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    @Override
    public KeyGenerator keyGenerator() {
        return (o, method, objects) -> {
            StringBuilder builder = new StringBuilder();
            builder.append(o.getClass().getName());
            builder.append(method.getName());
            for (Object object : objects) {
                builder.append(object);
            }
            return builder.toString();
        };
    }
}
