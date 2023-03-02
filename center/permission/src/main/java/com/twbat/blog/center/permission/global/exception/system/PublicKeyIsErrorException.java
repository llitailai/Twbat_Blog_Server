package com.twbat.blog.center.permission.global.exception.system;

import com.twbat.blog.center.permission.global.exception.SystemException;
import com.twbat.blog.center.permission.global.exception.enums.SystemExceptionEnum;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/9 - 21:00
 * @desciption 公钥错误异常
 */
public class PublicKeyIsErrorException extends SystemException {
    public PublicKeyIsErrorException(SystemExceptionEnum systemExceptionEnum) {
        super(systemExceptionEnum);
    }

    public PublicKeyIsErrorException(SystemExceptionEnum systemExceptionEnum, Throwable throwable) {
        super(systemExceptionEnum, throwable);
    }
}
