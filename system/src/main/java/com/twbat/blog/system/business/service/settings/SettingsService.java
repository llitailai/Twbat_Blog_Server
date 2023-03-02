package com.twbat.blog.system.business.service.settings;

import com.twbat.blog.common.util.util.ApiResult;
import com.twbat.blog.system.business.domain.user.po.user.SystemUserPo;
import com.twbat.blog.system.business.service.settings.component.basic.SystemSettingsBasicFunctionComponent;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/9 - 15:34
 * @desciption 系统设置服务类
 */
@Service
public class SettingsService {

    /**
     * 系统设置基础功能组件
     */
    @Resource
    private SystemSettingsBasicFunctionComponent settingsBasicFunctionComponent;

    /**
     * 创建登录密码加密公钥私钥
     * @param cur 当前用户
     * @return 全局统一返回对象
     */
    public ApiResult<?> createLoginPasswordKeys(SystemUserPo cur) {
        settingsBasicFunctionComponent.createLoginPasswordKeys(cur);
        return ApiResult.success();
    }


    /**
     * 获取登录面膜加密公钥与私钥
     * @return 全局统一返回对象
     */
    public ApiResult<?> getLoginPasswordKeys() {
        return ApiResult.success(settingsBasicFunctionComponent.getLoginPasswordKeys());
    }
}
