package cn.twbat.web.api.business.service.permissions.impl;

import cn.twbat.web.api.business.domain.permission.annotation.PermissionCode;
import cn.twbat.web.api.business.domain.permission.annotation.PermissionServiceAnnotation;
import cn.twbat.web.api.business.service.permissions.PermissionService;
import com.twbat.blog.center.permission.global.exception.business.permission.PermissionServiceNotFoundException;
import com.twbat.blog.center.permission.global.web.JwtUtil;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/26 - 15:18
 * @desciption
 */
@Service("basicPermissionService")
@PermissionServiceAnnotation("basicPermissionService")
public class PermissionServiceImpl implements PermissionService {


    @PermissionCode("permission:self")
    private void self(Object currentId) {
        if (currentId != null) {
            final Integer userId = JwtUtil.getUserId();
            if (userId.equals(Integer.valueOf(currentId.toString()))) {
                return;
            }
        }
        throw new PermissionServiceNotFoundException("无权限");
    }


    @Override
    public void permission(Method method, Object... params) throws InvocationTargetException, IllegalAccessException {
        method.invoke(this,params);
    }
}
