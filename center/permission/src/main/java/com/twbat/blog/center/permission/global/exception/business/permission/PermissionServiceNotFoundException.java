package com.twbat.blog.center.permission.global.exception.business.permission;

import com.twbat.blog.center.permission.global.exception.BusinessException;
import com.twbat.blog.center.permission.global.exception.enums.BusinessExceptionEnum;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/22 - 11:22
 * @desciption
 * 权限服务未找到异常
 */
public class PermissionServiceNotFoundException extends BusinessException {
    private String errorMsg;

    public PermissionServiceNotFoundException(BusinessExceptionEnum businessExceptionEnum) {
        super(businessExceptionEnum);
    }

    public PermissionServiceNotFoundException(BusinessExceptionEnum businessExceptionEnum, Throwable throwable) {
        super(businessExceptionEnum, throwable);
    }

    public PermissionServiceNotFoundException(String errorMsg) {
        super(null);
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
