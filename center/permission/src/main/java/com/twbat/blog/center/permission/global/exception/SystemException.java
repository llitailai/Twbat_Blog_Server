package com.twbat.blog.center.permission.global.exception;

import com.twbat.blog.center.permission.global.exception.enums.SystemExceptionEnum;

/**
 * @author darkltl
 * @className SystemException
 * @email darkltl@163.com
 * @date 2021/7/22 - 16:31
 * @description 系统异常
 */
public class SystemException extends RuntimeException {

    private final SystemExceptionEnum systemExceptionEnum;

    private Throwable throwable;

    public SystemException(final SystemExceptionEnum systemExceptionEnum) {
        this.systemExceptionEnum = systemExceptionEnum;
    }

    public SystemException(final SystemExceptionEnum systemExceptionEnum, final Throwable throwable) {
        this.systemExceptionEnum = systemExceptionEnum;
        this.throwable = throwable;
    }

    public final SystemExceptionEnum getSystemExceptionEnum() {
        return systemExceptionEnum;
    }

    public final Throwable getThrowable() {
        return throwable;
    }
}
