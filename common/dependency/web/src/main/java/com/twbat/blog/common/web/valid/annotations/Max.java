package com.twbat.blog.common.web.valid.annotations;

import java.lang.annotation.*;

/**
 * @author darkltl
 * @date 2021-09-08 16:45
 * 校验数值是否超出最大限制
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Documented
public @interface Max {

    int max() default 0;

    boolean open() default false;
}
