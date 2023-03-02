package com.twbat.blog.common.web.valid.exception;

import com.twbat.blog.common.web.valid.enums.ValidExceptionEnum;
import com.twbat.blog.common.web.valid.exception.base.ValidException;

/**
 * @author darkltl
 * @date 2021-09-10 09:37
 * 密码格式错误异常
 */
public class PasswordFormatException extends ValidException {

    public PasswordFormatException(ValidExceptionEnum validExceptionEnum) {
        super(validExceptionEnum);
    }

    public PasswordFormatException(ValidExceptionEnum validExceptionEnum, Throwable throwable) {
        super(validExceptionEnum, throwable);
    }
}
