package com.twbat.blog.center.permission.global.exception.valid;

import com.twbat.blog.center.permission.global.exception.BusinessException;
import com.twbat.blog.center.permission.global.exception.enums.BusinessExceptionEnum;

/**
 * @author darkltl
 * @date 2021-09-08 11:42
 * @description Token格式异常
 */
public class TokenFormatException extends BusinessException {

    public TokenFormatException(BusinessExceptionEnum businessExceptionEnum) {
        super(businessExceptionEnum);
    }

    public TokenFormatException(BusinessExceptionEnum businessExceptionEnum, Throwable throwable) {
        super(businessExceptionEnum, throwable);
    }
}
