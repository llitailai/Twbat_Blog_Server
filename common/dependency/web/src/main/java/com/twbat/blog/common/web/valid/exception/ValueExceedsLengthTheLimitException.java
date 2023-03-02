package com.twbat.blog.common.web.valid.exception;

import com.twbat.blog.common.web.valid.enums.ValidExceptionEnum;
import com.twbat.blog.common.web.valid.exception.base.ValidException;

/**
 * @author darkltl
 * @date 2021-09-10 09:49
 * 值超出长度最大限制异常
 */
public class ValueExceedsLengthTheLimitException extends ValidException {

    public ValueExceedsLengthTheLimitException(ValidExceptionEnum validExceptionEnum) {
        super(validExceptionEnum);
    }

    public ValueExceedsLengthTheLimitException(ValidExceptionEnum validExceptionEnum, Throwable throwable) {
        super(validExceptionEnum, throwable);
    }
}
