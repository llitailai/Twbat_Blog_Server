package cn.twbat.web.api.business.domain.permission.annotation;

import java.lang.annotation.*;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/18 - 10:42
 * @desciption
 * 权限服务注解,用于标注这是权限服务处理实现类
 * PermissionServiceAnnotation注解的value要与@Service注解的value一致
 * 如
 * ```
 * @PermissionServiceAnnotation("defaultPermissionService")
 * @Service("defaultPermissionService")
 * public class ...ServiceImpl(){}
 * ```
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
public @interface PermissionServiceAnnotation {

    String value() default "";



}
