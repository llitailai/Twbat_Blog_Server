package com.twbat.blog.common.web.valid.exception;

import com.twbat.blog.common.web.valid.enums.ValidExceptionEnum;
import com.twbat.blog.common.web.valid.exception.base.ValidException;

/**
 * @author darkltl
 * @date 2021-09-10 09:38
 * 值超出max最大限制异常
 */
public class ValueExceedsMaxTheLimitException extends ValidException {

    public ValueExceedsMaxTheLimitException(ValidExceptionEnum validExceptionEnum) {
        super(validExceptionEnum);
    }

    public ValueExceedsMaxTheLimitException(ValidExceptionEnum validExceptionEnum, Throwable throwable) {
        super(validExceptionEnum, throwable);
    }
}
