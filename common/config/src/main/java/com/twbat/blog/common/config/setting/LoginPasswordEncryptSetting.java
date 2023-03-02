package com.twbat.blog.common.config.setting;

import com.twbat.blog.common.config.setting.util.RsaUtil;

import java.util.Map;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/1/7 - 11:47
 * @desciption
 * 登陆密码解密设置
 */
public class LoginPasswordEncryptSetting {

    /**
     * 公私钥Map
     */
    private static final Map<String, String> KEYS;

    /*
     * 静态块默认执行创建公私钥
     */
    static {
        KEYS = RsaUtil.createKeys(1024);
    }

    /**
     * 获取公钥
     * @return 公钥
     */
    public static String getPublicKey() {
        return KEYS.get(RsaUtil.RSA_PUBLIC_KEY_STR);
    }

    /**
     * 获取私钥
     * @return 私钥
     */
    public static String getPrivateKey() {
        return KEYS.get(RsaUtil.RSA_PRIVATE_KEY_STR);
    }
}
