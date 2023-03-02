package com.twbat.blog.common.web.valid.exception;

import com.twbat.blog.common.web.valid.enums.ValidExceptionEnum;
import com.twbat.blog.common.web.valid.exception.base.ValidException;

/**
 * @author darkltl
 * @date 2021-09-10 09:36
 */
public class ParamCanNotBeNullException extends ValidException {


    public ParamCanNotBeNullException(ValidExceptionEnum validExceptionEnum) {
        super(validExceptionEnum);
    }

    public ParamCanNotBeNullException(ValidExceptionEnum validExceptionEnum, Throwable throwable) {
        super(validExceptionEnum, throwable);
    }
}
