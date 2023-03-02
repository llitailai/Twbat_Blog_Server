package com.twbat.blog.center.permission.global.exception.enums;

/**
 * @author darkltl
 * @className RuleExceptionEnum
 * @email darkltl@163.com
 * @date 2021/7/22 - 16:33
 * @description
 */
public enum RuleExceptionEnum {
    ;

    private Integer code;
    private String msg;

    RuleExceptionEnum(Integer code, String msg) {
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
