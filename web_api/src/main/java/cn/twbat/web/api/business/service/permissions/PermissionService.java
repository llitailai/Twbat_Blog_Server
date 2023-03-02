package cn.twbat.web.api.business.service.permissions;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/18 - 10:37
 * @desciption
 * 权限服务
 */
public interface PermissionService {


    /**
     * 权限验证执行函数
     * @param method 具体权限验证功能实现的函数
     * @param params 参数
     * @throws InvocationTargetException 执行目标函数时出现异常
     * @throws IllegalAccessException  非法访问异常
     */
    void permission(Method method, Object ... params) throws InvocationTargetException, IllegalAccessException ;
}
