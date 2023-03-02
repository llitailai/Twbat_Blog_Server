package com.twbat.blog.system.business.service.settings.fegin;

import com.alibaba.fastjson.JSONObject;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/9 - 17:59
 * @desciption 系统设置对外调用服务定义类
 */
public interface SettingsFeignService {

    /**
     * 获取登录密码所需要的加密公钥
     *
     * @return 公钥json对象
     */
    JSONObject getLoginPasswordPublicKey();

    /**
     * 获取登录密码所需要的加密私钥
     *
     * @return 私钥json对象
     */
    JSONObject getLoginPasswordPrivateKey();
}
