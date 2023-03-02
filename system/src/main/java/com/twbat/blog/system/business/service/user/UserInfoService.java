package com.twbat.blog.system.business.service.user;

import com.twbat.blog.common.util.util.ApiResult;

/**
 * @author darkltl
 * @date 2021-09-14 10:51
 * 用户信息服务定义类
 */
public interface UserInfoService {

    /**
     * 获取全局系统用户
     * todo: 后续需要完善
     *
     * @return 全局统一返回对象
     */
    ApiResult<?> listSystemUsers();
}
