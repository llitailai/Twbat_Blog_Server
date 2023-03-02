package com.twbat.blog.system.controller.settings;

import com.alibaba.fastjson.JSONObject;
import com.twbat.blog.system.business.service.settings.fegin.SettingsFeignService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/9 - 18:21
 * @desciption 系统设置对外开放调用控制器
 */
@RestController
@RequestMapping("/feign/setting")
public class SystemSettingFeignController {

    /**
     * 系统设置对外调用服务定义类
     */
    @Resource
    private SettingsFeignService settingsFeignService;

    /**
     * 获取登录密码加密公钥
     * @return 全局统一返回对象
     */
    @GetMapping("/getPubKey")
    public JSONObject getLoginPasswordPublicKey() {
        return settingsFeignService.getLoginPasswordPublicKey();
    }

    /**
     * 获取登录密码加密私钥
     * @return 全局统一返回对象
     */
    @GetMapping("/getPriKey")
    public JSONObject getLoginPasswordPrivateKey() {
        return settingsFeignService.getLoginPasswordPrivateKey();
    }
}
