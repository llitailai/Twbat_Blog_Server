package com.twbat.blog.center.permission.global.exception;

import com.twbat.blog.center.permission.global.exception.enums.RuleExceptionEnum;

/**
 * @author darkltl
 * @className RuleException
 * @email darkltl@163.com
 * @date 2021/7/22 - 16:31
 * @description 规则异常
 */
public class RuleException extends RuntimeException {

    private final RuleExceptionEnum ruleExceptionEnum;

    private Throwable throwable;

    public RuleException(final RuleExceptionEnum ruleExceptionEnum) {
        this.ruleExceptionEnum = ruleExceptionEnum;
    }

    public RuleException(final RuleExceptionEnum ruleExceptionEnum, Throwable throwable) {
        this.ruleExceptionEnum = ruleExceptionEnum;
        this.throwable = throwable;
    }

    public final Throwable getThrowable() {
        return throwable;
    }

    public RuleExceptionEnum getRuleExceptionEnum() {
        return ruleExceptionEnum;
    }

}
