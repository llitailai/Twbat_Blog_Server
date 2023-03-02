package com.twbat.blog.common.util.util.auth.annotaions;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author darkltl
 * @className Required
 * @email darkltl@163.com
 * @date 2021/8/16 - 13:47
 * @description Token 注解
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Required {

    boolean required() default true;

}
