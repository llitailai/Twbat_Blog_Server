package com.twbat.blog.center.permission.global.exception.business.file;

import com.twbat.blog.center.permission.global.exception.BusinessException;
import com.twbat.blog.center.permission.global.exception.enums.BusinessExceptionEnum;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/10 - 17:57
 * @desciption
 */
public class FileNotFoundException extends BusinessException {
    public FileNotFoundException(BusinessExceptionEnum businessExceptionEnum) {
        super(businessExceptionEnum);
    }

    public FileNotFoundException(BusinessExceptionEnum businessExceptionEnum, Throwable throwable) {
        super(businessExceptionEnum, throwable);
    }
}
