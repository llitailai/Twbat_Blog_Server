package com.twbat.blog.common.web.valid.enums;

/**
 * @author darkltl
 * @date 2021-09-10 09:52
 * @description 校验异常枚举
 * 校验异常code为600
 */
public enum ValidExceptionEnum {

    /**
     * 参数为空错误异常枚举
     */
    PARAM_CAN_NOT_BE_NULL_EXCEPTION_ENUM(601, "参数不可为空"),
    /**
     * 密码格式错误异常枚举
     */
    PASSWORD_FORMAT_EXCEPTION_ENUM(602, "密码格式错误"),
    /**
     * 电话格式错误异常枚举
     */
    TEL_FORMAT_EXCEPTION_ENUM(603, "电话格式错误"),
    /**
     * 值超出最大限制异常枚举
     */
    VALUE_EXCEEDS_MAX_THE_LIMIT_EXCEPTION_ENUM(604, "值超出最大限制"),
    /**
     * 值超出最小限制异常枚举
     */
    VALUE_EXCEEDS_MIN_THE_LIMIT_EXCEPTION_ENUM(605, "值超出最小限制"),
    /**
     * 值超出最大长度限制异常枚举
     */
    VALUE_EXCEEDS_LENGTH_THE_LIMIT_EXCEPTION_ENUM(606, "值超出长度最大限制"),
    /**
     * 值超出长度最小限制
     */
    VALUE_EXCEEDS_MIN_LENGTH_THE_LIMIT_EXCEPTION_ENUM(607, "值超出长度最小限制"),
    ;

    /**
     * 错误状态码
     */
    private final Integer code;

    /**
     * 错误提示西南西
     */
    private final String msg;


    ValidExceptionEnum(final Integer code, final String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
