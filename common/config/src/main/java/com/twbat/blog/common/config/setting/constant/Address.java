package com.twbat.blog.common.config.setting.constant;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/9 - 18:33
 * @desciption 远程服务调用地址常量
 */
public class Address {

    /**
     * 登录密码安全公钥远程调用地址
     */
    public static final String LOGIN_PASSWORD_PUBLIC_KEY_FEIGN_URL = "/feign/setting/getPubKey";

    public static final String LOGIN_PASSWORD_PRIVATE_KEY_FEIGN_URL = "/feign/setting/getPriKey";

    public static final String COMMON_UPLOAD_FEIGN_URL = "/system/upload";
}
