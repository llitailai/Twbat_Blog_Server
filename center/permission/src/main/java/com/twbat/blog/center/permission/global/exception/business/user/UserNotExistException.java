package com.twbat.blog.center.permission.global.exception.business.user;

import com.twbat.blog.center.permission.global.exception.BusinessException;
import com.twbat.blog.center.permission.global.exception.enums.BusinessExceptionEnum;


public class UserNotExistException extends BusinessException {
    public UserNotExistException(BusinessExceptionEnum businessExceptionEnum) {
        super(businessExceptionEnum);
    }

    public UserNotExistException(BusinessExceptionEnum businessExceptionEnum, Throwable throwable) {
        super(businessExceptionEnum, throwable);
    }
}
