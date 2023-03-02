package com.twbat.blog.system.controller.settings;

import com.twbat.blog.common.util.util.ApiResult;
import com.twbat.blog.common.util.util.auth.annotaions.Required;
import com.twbat.blog.system.base.BaseController;
import com.twbat.blog.system.business.service.settings.SettingsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/9 - 15:52
 * @desciption 系统设置控制器
 */
@RestController
public class SystemSettingsController extends BaseController {

    /**
     * 系统设置服务,用于提供对系统设置操作的服务支持
     */
    @Resource
    private SettingsService settingsService;

    /**
     * 创建登录密码时所需的密钥
     *
     * @return 创建成功或失败的全局统一返回对象
     */
    @Required
    @GetMapping("/login/password/createKeys")
    public ApiResult<?> createLoginPasswordKeys() {
        return settingsService.createLoginPasswordKeys(getCurrentUser());
    }


    /**
     * 获取登录密码时所需的公钥与私钥
     * @return 全局统一返回对象
     */
    @SuppressWarnings("all")
    //    @Permission(permissionCode = "system:settings:view",require = true)
    @GetMapping("/login/password/getKeys")
    public ApiResult<?> getLoginPasswordKeys() {
        return settingsService.getLoginPasswordKeys();
    }


}
