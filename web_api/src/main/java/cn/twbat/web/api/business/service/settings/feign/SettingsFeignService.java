package cn.twbat.web.api.business.service.settings.feign;

import com.alibaba.fastjson.JSONObject;
import com.twbat.blog.common.config.setting.constant.Address;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/9 - 17:59
 * @desciption 系统设置对外调用服务定义类
 */
@FeignClient(url = "${setting-url}", name = "settingUrl")
public interface SettingsFeignService {

    /**
     * 获取登录密码所需要的加密公钥
     *
     * @return 加密公钥
     */
    @GetMapping(Address.LOGIN_PASSWORD_PUBLIC_KEY_FEIGN_URL)
    JSONObject getLoginPasswordPublicKey();

    /**
     * 获取登录密码所需要的加密私钥
     *
     * @return 加密私钥
     */
    @GetMapping(Address.LOGIN_PASSWORD_PRIVATE_KEY_FEIGN_URL)
    JSONObject getLoginPasswordPrivateKey();
}
