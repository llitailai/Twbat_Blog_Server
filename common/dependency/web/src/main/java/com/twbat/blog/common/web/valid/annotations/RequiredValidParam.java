package com.twbat.blog.common.web.valid.annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
@Inherited
public @interface RequiredValidParam {
    boolean value() default true;
}
