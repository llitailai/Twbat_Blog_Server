package com.twbat.blog.common.web.valid.annotations;


import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Documented
public @interface Regex {

    String tel() default "";

    String password() default "";

    String email() default "";
}
