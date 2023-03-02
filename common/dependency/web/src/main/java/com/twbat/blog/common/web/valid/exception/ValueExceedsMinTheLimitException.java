package com.twbat.blog.common.web.valid.exception;

import com.twbat.blog.common.web.valid.enums.ValidExceptionEnum;
import com.twbat.blog.common.web.valid.exception.base.ValidException;

/**
 * @author darkltl
 * @date 2021-09-10 09:40
 * 值超出min最小限制异常
 */
public class ValueExceedsMinTheLimitException extends ValidException {

    public ValueExceedsMinTheLimitException(ValidExceptionEnum validExceptionEnum) {
        super(validExceptionEnum);
    }

    public ValueExceedsMinTheLimitException(ValidExceptionEnum validExceptionEnum, Throwable throwable) {
        super(validExceptionEnum, throwable);
    }
}
