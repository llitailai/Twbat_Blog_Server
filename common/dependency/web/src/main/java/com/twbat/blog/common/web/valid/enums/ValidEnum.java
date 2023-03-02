package com.twbat.blog.common.web.valid.enums;

/**
 * @author darkltl
 * @email darkltl@163.com
 * @description 校验枚举
 */
public enum ValidEnum {
    /**
     * 为空校验
     */
    VALID,
    /**
     * 电话校验
     */
    TEL,
    /**
     * 密码校验
     */
    PASSWORD,
    /**
     * 最大值校验
     */
    MAX,
    /**
     * 最小值校验
     */
    MIN,
    /**
     * 长度校验(小于)
     */
    LENGTH,
    /**
     * 长度校验(大于)
     */
    LENGTH_GT,
    /**
     * 邮箱校验
     */
    EMAIL;
}
