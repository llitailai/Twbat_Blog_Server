package com.twbat.blog.center.permission.global.exception.system;

import com.twbat.blog.center.permission.global.exception.SystemException;
import com.twbat.blog.center.permission.global.exception.enums.SystemExceptionEnum;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/12 - 22:11
 * @desciption
 */
public class IllegalAccessException extends SystemException {

    public IllegalAccessException(SystemExceptionEnum systemExceptionEnum) {
        super(systemExceptionEnum);
    }

    public IllegalAccessException(SystemExceptionEnum systemExceptionEnum, Throwable throwable) {
        super(systemExceptionEnum, throwable);
    }
}
