package com.twbat.blog.system.business.service.impl.settings;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.twbat.blog.common.config.setting.enums.SystemSettingEnum;
import com.twbat.blog.system.business.domain.settings.po.SystemSettingsPo;
import com.twbat.blog.system.business.repository.settings.SystemSettingsMapper;
import com.twbat.blog.system.business.service.settings.fegin.SettingsFeignService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/9 - 18:09
 * @desciption 系统设置对外调用服务实现类
 */
@Service
public class SettingsFeignServiceImpl implements SettingsFeignService {

    @Resource
    private SystemSettingsMapper systemSettingsMapper;

    @Override
    public JSONObject getLoginPasswordPublicKey() {

        SystemSettingsPo res = systemSettingsMapper.selectOne(getPasswordKeyConditionByFlag(0));
        JSONObject resJson = new JSONObject();
        resJson.put("key", res.getSettingValue());
        return resJson;
    }

    @Override
    public JSONObject getLoginPasswordPrivateKey() {
        SystemSettingsPo res = systemSettingsMapper.selectOne(getPasswordKeyConditionByFlag(1));
        JSONObject resJson = new JSONObject();
        resJson.put("key", res.getSettingValue());
        return resJson;
    }

    /**
     * 根据flag获取对应的条件wrapper
     * @param flag 公钥/私钥 标识
     * @return 条件wrapper
     */
    private LambdaQueryWrapper<SystemSettingsPo> getPasswordKeyConditionByFlag(int flag) {
        String code = "default";
        if (flag == 0) {
            code = SystemSettingEnum.LOGIN_PASSWORD_PUBLIC_KEY.getCode();
        } else if (flag == 1) {
            code = SystemSettingEnum.LOGIN_PASSWORD_PRIVATE_KEY.getCode();
        }
        return new LambdaQueryWrapper<SystemSettingsPo>().select(
                SystemSettingsPo::getSettingCode,
                SystemSettingsPo::getSettingValue
        ).eq(
                SystemSettingsPo::getSettingCode, code
        );

    }
}
