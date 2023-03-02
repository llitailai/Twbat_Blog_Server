package com.twbat.blog.center.permission.global.exception.business.user;

import com.twbat.blog.center.permission.global.exception.BusinessException;
import com.twbat.blog.center.permission.global.exception.enums.BusinessExceptionEnum;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/17 - 21:25
 * @desciption
 * 用户两次密码不一致异常
 */
public class UserPasswordNotIdenticalException extends BusinessException {
    public UserPasswordNotIdenticalException(BusinessExceptionEnum businessExceptionEnum) {
        super(businessExceptionEnum);
    }

    public UserPasswordNotIdenticalException(BusinessExceptionEnum businessExceptionEnum, Throwable throwable) {
        super(businessExceptionEnum, throwable);
    }
}
