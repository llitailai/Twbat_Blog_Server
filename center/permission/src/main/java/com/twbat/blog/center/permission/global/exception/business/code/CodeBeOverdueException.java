package com.twbat.blog.center.permission.global.exception.business.code;

import com.twbat.blog.center.permission.global.exception.BusinessException;
import com.twbat.blog.center.permission.global.exception.enums.BusinessExceptionEnum;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/17 - 21:20
 * @desciption
 * 验证码过期异常
 */
public class CodeBeOverdueException extends BusinessException {
    public CodeBeOverdueException(BusinessExceptionEnum businessExceptionEnum) {
        super(businessExceptionEnum);
    }

    public CodeBeOverdueException(BusinessExceptionEnum businessExceptionEnum, Throwable throwable) {
        super(businessExceptionEnum, throwable);
    }
}
