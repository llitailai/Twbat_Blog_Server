package com.twbat.blog.center.permission.global.exception.business.user;

import com.twbat.blog.center.permission.global.exception.BusinessException;
import com.twbat.blog.center.permission.global.exception.enums.BusinessExceptionEnum;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/10 - 17:05
 * @desciption 用户信息已存在异常
 */
public class UserInfoIsExistException extends BusinessException {
    public UserInfoIsExistException(BusinessExceptionEnum businessExceptionEnum) {
        super(businessExceptionEnum);
    }

    public UserInfoIsExistException(BusinessExceptionEnum businessExceptionEnum, Throwable throwable) {
        super(businessExceptionEnum, throwable);
    }
}
