package com.twbat.blog.common.web.valid.annotations;

import java.lang.annotation.*;

/**
 *
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 * @author darkltl
 * @date 2022-02-17 22:58
 * @desciption 校验注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Documented
@Inherited
public @interface Valid {

    /**
     * 校验是否为空
     * 默认开启
     *
     * @return boolean
     * true : 开启校验
     * false : 不开启校验
     */
    boolean valid() default true;

    /**
     * 手机号校验
     *
     * @return boolean
     * true : 开启校验
     * false : 不开启校验
     */
    boolean tel() default false;

    /**
     * 邮箱验证
     * @return boolean
     * true : 开启校验
     * false: 不开启校验
     */
    boolean email() default false;

    /**
     * 密码校验
     *
     * @return boolean
     * true : 校验密码是否符合格式
     * false : 不开启密码校验
     */
    boolean password() default false;

    /**
     * 最大值校验,应用于数值
     * 如 传入的参数 为 100, max 的参数为10 100>10 所以校验不通过
     *
     * @return Max
     */
    Max max() default @Max();

    /**
     * 与最大值校验逻辑相反
     *
     * @return
     */
    Min min() default @Min();

    /**
     * 校验字符串长度是否符合要求
     * 必须要小于该长度
     *
     * @return
     */
    int lengthLt() default -1;

    /**
     * 校验字符串长度是否符合要求
     * 必须要大于该长度
     *
     * @return
     */
    int lengthGt() default -1;

    Regex regex() default @Regex();
}
