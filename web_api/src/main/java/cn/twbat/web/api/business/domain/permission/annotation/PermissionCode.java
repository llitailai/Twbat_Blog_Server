package cn.twbat.web.api.business.domain.permission.annotation;

import java.lang.annotation.*;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/18 - 10:47
 * @desciption
 * 权限代码标注注解
 * 以函数为基准
 * 如 :
 * ```
 * @PermissionCode()
 * public void ...function(){}
 * ```
 * 但是@PermissionCode只会再@PermissionService()注解的类生效
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface PermissionCode {

    String value() default "";
}
