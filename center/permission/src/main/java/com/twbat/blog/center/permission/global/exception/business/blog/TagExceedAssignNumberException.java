package com.twbat.blog.center.permission.global.exception.business.blog;

import com.twbat.blog.center.permission.global.exception.BusinessException;
import com.twbat.blog.center.permission.global.exception.enums.BusinessExceptionEnum;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/13 - 20:49
 * @desciption
 */
public class TagExceedAssignNumberException extends BusinessException {
    public TagExceedAssignNumberException(BusinessExceptionEnum businessExceptionEnum) {
        super(businessExceptionEnum);
    }

    public TagExceedAssignNumberException(BusinessExceptionEnum businessExceptionEnum, Throwable throwable) {
        super(businessExceptionEnum, throwable);
    }
}
