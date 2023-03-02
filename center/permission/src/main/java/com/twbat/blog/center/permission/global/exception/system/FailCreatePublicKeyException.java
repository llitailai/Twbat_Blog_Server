package com.twbat.blog.center.permission.global.exception.system;

import com.twbat.blog.center.permission.global.exception.SystemException;
import com.twbat.blog.center.permission.global.exception.enums.SystemExceptionEnum;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/9 - 15:24
 * @desciption 创建公钥失败异常
 */
public class FailCreatePublicKeyException extends SystemException {

    public FailCreatePublicKeyException(SystemExceptionEnum systemExceptionEnum) {
        super(systemExceptionEnum);
    }

    public FailCreatePublicKeyException(SystemExceptionEnum systemExceptionEnum, Throwable throwable) {
        super(systemExceptionEnum, throwable);
    }
}
