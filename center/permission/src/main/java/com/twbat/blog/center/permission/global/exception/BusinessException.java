package com.twbat.blog.center.permission.global.exception;

import com.twbat.blog.center.permission.global.exception.enums.BusinessExceptionEnum;

/**
 * @author darkltl
 * @className BusinessException
 * @email darkltl@163.com
 * @date 2021/7/22 - 16:32
 * @description 业务异常
 */
public class BusinessException extends RuntimeException {

    private final BusinessExceptionEnum businessExceptionEnum;

    private Throwable throwable;

    public BusinessException(final BusinessExceptionEnum businessExceptionEnum) {
        this.businessExceptionEnum = businessExceptionEnum;
    }

    public BusinessException(final BusinessExceptionEnum businessExceptionEnum, Throwable throwable) {
        this.businessExceptionEnum = businessExceptionEnum;
        this.throwable = throwable;
    }

    public final Throwable getThrowable() {
        return throwable;
    }

    public final BusinessExceptionEnum getBusinessExceptionEnum() {
        return businessExceptionEnum;
    }

}
