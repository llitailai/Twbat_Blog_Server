package com.twbat.blog.center.permission.global.exception.valid;

import com.twbat.blog.center.permission.global.exception.BusinessException;
import com.twbat.blog.center.permission.global.exception.enums.BusinessExceptionEnum;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/10 - 1:28
 * @desciption Token失效异常
 */
public class TokenInvalidationException extends BusinessException {
    public TokenInvalidationException(BusinessExceptionEnum businessExceptionEnum) {
        super(businessExceptionEnum);
    }

    public TokenInvalidationException(BusinessExceptionEnum businessExceptionEnum, Throwable throwable) {
        super(businessExceptionEnum, throwable);
    }
}
