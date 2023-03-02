package com.twbat.blog.center.permission.global.exception.register;

import com.twbat.blog.center.permission.global.exception.BusinessException;
import com.twbat.blog.center.permission.global.exception.enums.BusinessExceptionEnum;

/**
 * @author darkltl
 * @date 2021-09-10 16:16
 * 用户注册手机号已存在异常
 */
public class RegisterPhoneExistException extends BusinessException {

    public RegisterPhoneExistException(BusinessExceptionEnum businessExceptionEnum) {
        super(businessExceptionEnum);
    }

    public RegisterPhoneExistException(BusinessExceptionEnum businessExceptionEnum, Throwable throwable) {
        super(businessExceptionEnum, throwable);
    }
}
