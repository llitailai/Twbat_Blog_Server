package cn.twbat.web.api.business.service.permissions.impl;

import cn.twbat.web.api.business.domain.permission.annotation.PermissionServiceAnnotation;
import cn.twbat.web.api.business.service.permissions.PermissionService;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/18 - 10:55
 * @desciption
 * 默认权限处理实现类
 * 只是作为演示,并不具体实现
 */
@Deprecated
@PermissionServiceAnnotation("default")
public class DefaultPermissionServiceImpl implements PermissionService {

    @Override
    public void permission(Method method, Object... params) throws InvocationTargetException, IllegalAccessException {

    }
}
