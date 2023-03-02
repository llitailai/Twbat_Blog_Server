package com.twbat.blog.center.permission.annotation;

import java.lang.annotation.*;

/**
 * @author darkltl
 * @className Permission
 * @email darkltl@163.com
 * @date 2021/7/22 - 14:59
 * @description 权限注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface Permission {

    /**
     * 是否需要权限校验
     * true: 需要
     * false : 不需要
     *
     * @return boolean
     */
    boolean require() default true;

    /**
     * 权限代码
     *
     * @return
     */
    String permissionCode();
}
