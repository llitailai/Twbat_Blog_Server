package com.twbat.blog.system.controller.login;

import com.twbat.blog.common.util.util.ApiResult;
import com.twbat.blog.common.web.valid.annotations.RequiredValidParam;
import com.twbat.blog.common.web.valid.annotations.Valid;
import com.twbat.blog.system.base.BaseController;
import com.twbat.blog.system.business.domain.user.dto.user.login.SystemLoginDto;
import com.twbat.blog.system.business.service.login.LoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022-02-17 - 11:15
 * @desciption 用户登录控制器
 */
@RestController
public class UserLoginController extends BaseController {

    /**
     * 用户登录服务定义类
     * 用于提供用户登录服务支持
     */
    @Resource
    private LoginService loginService;


    /**
     * 系统用户登录接口
     * @param loginDto 登录参数dto
     * @return 全局统一返回对象
     */
    @PostMapping("/login")
    @RequiredValidParam
    public ApiResult<?> userLoginController(@Valid @RequestBody SystemLoginDto loginDto) {
        return loginService.loginService(loginDto);
    }

}
