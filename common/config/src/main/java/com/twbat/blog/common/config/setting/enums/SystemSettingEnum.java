package com.twbat.blog.common.config.setting.enums;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/9 - 13:39
 * @desciption 系统设置枚举
 */
public enum SystemSettingEnum {
    /**
     * 登录密码安全公钥
     */
    LOGIN_PASSWORD_PUBLIC_KEY("登录密码安全公钥", "login_password_public_key"),
    /**
     * 登录密码安全私钥
     */
    LOGIN_PASSWORD_PRIVATE_KEY("登录密码安全私钥", "login_password_private_key"),
    ;
    /**
     * 设置名称
     */
    private final String name;

    /**
     * 代码
     */
    private final String code;

    private SystemSettingEnum(final String name, final String code) {
        this.name = name;
        this.code = code;
    }

    public final String getCode() {
        return this.code;
    }

    public final String getName() {
        return this.name;
    }
}
