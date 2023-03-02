package com.twbat.blog.system.controller.info;

import com.twbat.blog.common.util.util.ApiResult;
import com.twbat.blog.common.util.util.auth.annotaions.Required;
import com.twbat.blog.system.base.BaseController;
import com.twbat.blog.system.business.service.user.UserInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022-02-17 11:14
 * @desciption 系统用户信息控制器
 */
@RestController
public class SystemUserInfoController extends BaseController {

    /**
     * 用户信息服务
     */
    @Resource
    private UserInfoService userInfoService;

    /**
     * 获取全部系统用户
     * todo: 后续需要加以完善
     *
     * @return 全局统一返回对象
     */
    @GetMapping("/user/listUsers")
    public ApiResult<?> listUsers() {
        return userInfoService.listSystemUsers();
    }

    /**
     * 获取当前登录系统用户信息
     *
     * @return 全局统一返回对象
     */
    @Required
    @GetMapping("/user/getUserInfo")
    public ApiResult<?> getCurrentUserInfo() {
        return ApiResult.success(getCurrentUser());
    }

}
