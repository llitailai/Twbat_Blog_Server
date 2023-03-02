package com.twbat.blog.common.web.valid.exception;

import com.twbat.blog.common.web.valid.enums.ValidExceptionEnum;
import com.twbat.blog.common.web.valid.exception.base.ValidException;

/**
 * @author darkltl
 * @date 2021-09-10 09:35
 * 电话格式异常
 */
public class TelFormatException extends ValidException {

    public TelFormatException(ValidExceptionEnum validExceptionEnum) {
        super(validExceptionEnum);
    }

    public TelFormatException(ValidExceptionEnum validExceptionEnum, Throwable throwable) {
        super(validExceptionEnum, throwable);
    }
}
