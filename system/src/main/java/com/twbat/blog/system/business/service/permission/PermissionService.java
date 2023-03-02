package com.twbat.blog.system.business.service.permission;

import com.twbat.blog.common.util.util.ApiResult;

/**
 * @author darkltl
 * @date 2021-09-14 13:20
 * 权限服务定义类
 */
public interface PermissionService {

    /**
     * 获取权限
     * @return 全局统一返回对象
     */
    ApiResult<?> getPermissions();
}
