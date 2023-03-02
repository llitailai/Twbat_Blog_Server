package com.twbat.blog.system.business.service.settings.component.basic;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.twbat.blog.center.permission.global.exception.enums.SystemExceptionEnum;
import com.twbat.blog.center.permission.global.exception.system.FailCreatePublicKeyException;
import com.twbat.blog.common.config.setting.LoginPasswordEncryptSetting;
import com.twbat.blog.common.config.setting.enums.SystemSettingEnum;
import com.twbat.blog.system.business.domain.settings.po.SystemSettingsPo;
import com.twbat.blog.system.business.domain.settings.vo.LoginPasswordKeysVo;
import com.twbat.blog.system.business.domain.user.po.user.SystemUserPo;
import com.twbat.blog.system.business.repository.settings.SystemSettingsMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/9 - 15:37
 * @desciption 系统设置基础功能组件
 */
@Component
public class SystemSettingsBasicFunctionComponent {

    /**
     * 系统设置DAO
     */
    @Resource
    private SystemSettingsMapper systemSettingsMapper;

    /**
     * 创建登录密码密钥
     * 该函数会在未创建密钥时主动运行
     * 创建密钥后插入数据库,该密钥不可重复
     *
     * @param cur 当前执行用户
     */
    public void createLoginPasswordKeys(SystemUserPo cur) {

        /*
         * 获取创建后的公钥
         */
        SystemSettingsPo publicKeySetting = SystemSettingsPo.builder()
                .settingCode(SystemSettingEnum.LOGIN_PASSWORD_PUBLIC_KEY.getCode())
                .settingName(SystemSettingEnum.LOGIN_PASSWORD_PUBLIC_KEY.getName())
                .settingValue(LoginPasswordEncryptSetting.getPublicKey())
                .createUser(cur.getUserTel()).build();
        if (systemSettingsMapper.insert(publicKeySetting) <= 0) {
            throw new FailCreatePublicKeyException(SystemExceptionEnum.CREATE_PUBLIC_KEY_EXCEPTION);
        }

        /*
         * 获取创建后的私钥
         */
        SystemSettingsPo privateKeySetting = SystemSettingsPo.builder()
                .settingCode(SystemSettingEnum.LOGIN_PASSWORD_PRIVATE_KEY.getCode())
                .settingName(SystemSettingEnum.LOGIN_PASSWORD_PRIVATE_KEY.getName())
                .settingValue(LoginPasswordEncryptSetting.getPrivateKey())
                .createUser(cur.getUserTel()).build();

        if (systemSettingsMapper.insert(privateKeySetting) <= 0) {
            throw new FailCreatePublicKeyException(SystemExceptionEnum.CREATE_PUBLIC_KEY_EXCEPTION);
        }
    }

    /**
     * 获取登录时所需密钥
     *
     * @return 密钥vo
     */
    public LoginPasswordKeysVo
    getLoginPasswordKeys() {
        List<SystemSettingsPo> systemSettingsPos = systemSettingsMapper.selectList(
                getLoginPasswordPublicKeyCondition()
        );
        LoginPasswordKeysVo result = new LoginPasswordKeysVo();
        systemSettingsPos.forEach(
                cur -> {
                    if (SystemSettingEnum.LOGIN_PASSWORD_PUBLIC_KEY.getCode().equals(cur.getSettingCode())) {
                        result.setPublicKey(cur.getSettingValue());
                    } else {
                        result.setPrivateKey(cur.getSettingValue());
                    }
                }
        );

        return result;
    }

    /**
     * 获取登录密码加密公钥私钥条件wrapper
     * @return 条件wrapper
     */
    private LambdaQueryWrapper<SystemSettingsPo> getLoginPasswordPublicKeyCondition() {
        return new LambdaQueryWrapper<SystemSettingsPo>()
                .select(SystemSettingsPo::getSettingCode,
                        SystemSettingsPo::getSettingValue)
                .eq(SystemSettingsPo::getSettingCode, SystemSettingEnum.LOGIN_PASSWORD_PUBLIC_KEY.getCode())
                .or()
                .eq(SystemSettingsPo::getSettingCode, SystemSettingEnum.LOGIN_PASSWORD_PRIVATE_KEY.getCode());
    }

}
