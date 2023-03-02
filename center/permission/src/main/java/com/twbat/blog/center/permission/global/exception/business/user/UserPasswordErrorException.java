package com.twbat.blog.center.permission.global.exception.business.user;

import com.twbat.blog.center.permission.global.exception.BusinessException;
import com.twbat.blog.center.permission.global.exception.enums.BusinessExceptionEnum;


public class UserPasswordErrorException extends BusinessException {
    public UserPasswordErrorException(BusinessExceptionEnum businessExceptionEnum) {
        super(businessExceptionEnum);
    }

    public UserPasswordErrorException(BusinessExceptionEnum businessExceptionEnum, Throwable throwable) {
        super(businessExceptionEnum, throwable);
    }
}
