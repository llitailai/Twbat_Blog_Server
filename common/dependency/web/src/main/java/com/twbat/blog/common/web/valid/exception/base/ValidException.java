package com.twbat.blog.common.web.valid.exception.base;

import com.twbat.blog.common.web.valid.enums.ValidExceptionEnum;

public class ValidException extends RuntimeException {

    private final ValidExceptionEnum validExceptionEnum;

    private Throwable throwable;

    public ValidException(ValidExceptionEnum validExceptionEnum) {
        this.validExceptionEnum = validExceptionEnum;
    }

    public ValidException(ValidExceptionEnum validExceptionEnum, Throwable throwable) {
        this.validExceptionEnum = validExceptionEnum;
        this.throwable = throwable;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public ValidExceptionEnum getValidExceptionEnum() {
        return validExceptionEnum;
    }
}
