package com.twbat.blog.center.permission.global.exception.business.email;

import com.twbat.blog.center.permission.global.exception.BusinessException;
import com.twbat.blog.center.permission.global.exception.enums.BusinessExceptionEnum;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/18 - 1:44
 * @desciption
 * 邮箱已被使用异常
 */
public class EmailIsAlreadyUseException extends BusinessException {
    public EmailIsAlreadyUseException(BusinessExceptionEnum businessExceptionEnum) {
        super(businessExceptionEnum);
    }

    public EmailIsAlreadyUseException(BusinessExceptionEnum businessExceptionEnum, Throwable throwable) {
        super(businessExceptionEnum, throwable);
    }
}
