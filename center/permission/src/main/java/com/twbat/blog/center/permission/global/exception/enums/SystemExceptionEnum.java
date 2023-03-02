package com.twbat.blog.center.permission.global.exception.enums;

/**
 * @author darkltl
 * @className SystemExceptionEnum
 * @email darkltl@163.com
 * @date 2021/7/22 - 16:34
 * @description
 */
public enum SystemExceptionEnum {
    CREATE_PUBLIC_KEY_EXCEPTION(500, "创建公钥失败"),
    PUBLIC_KEY_ERROR_EXCEPTION(500, "公钥错误"),
    ERROR(500, "系统异常"),
    SEND_EMAIL_EXCEPTION(500, "邮件发送失败,请重试"),
    ILLEGAL_ACCESS_EXCEPTION(403, "非法访问"),
    ;

    private Integer code;
    private String msg;

    SystemExceptionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getMsg() {
        return this.msg;
    }

    public Integer getCode() {
        return code;
    }
}
