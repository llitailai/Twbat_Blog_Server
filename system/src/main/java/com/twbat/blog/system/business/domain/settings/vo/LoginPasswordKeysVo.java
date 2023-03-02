package com.twbat.blog.system.business.domain.settings.vo;

import lombok.Data;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/9 - 15:30
 * @desciption 登录密码密钥VO对象
 */
@Data
public class LoginPasswordKeysVo {

    /**
     * 公钥
     */
    private String publicKey;

    /**
     * 私钥
     */
    private String privateKey;
}
