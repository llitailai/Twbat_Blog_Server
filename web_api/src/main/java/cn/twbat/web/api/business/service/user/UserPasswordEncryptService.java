package cn.twbat.web.api.business.service.user;

import cn.twbat.web.api.business.service.settings.feign.SettingsFeignService;
import com.alibaba.fastjson.JSONObject;
import com.twbat.blog.center.permission.global.exception.SystemException;
import com.twbat.blog.center.permission.global.exception.enums.SystemExceptionEnum;
import com.twbat.blog.center.permission.global.exception.system.PublicKeyIsErrorException;
import com.twbat.blog.common.config.setting.util.RsaUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/17 - 22:24
 * @desciption
 */
@Service
public class UserPasswordEncryptService {

    /**
     * 系统设置Feign调用服务
     * 用于调用服务端接口所提供的服务类
     */
    @Resource
    private SettingsFeignService settingsFeignService;

    /**
     * 将前端传递过来的密码 (通过公钥加密后的) 进行解密
     * @param password
     * @return 当前对象
     * @exception SystemException 公钥错误
     */
    public String convertPassword(String password) {
        try {
            // 获取私钥
            JSONObject pri = settingsFeignService.getLoginPasswordPrivateKey();
            // 通过返回的JSONObject获取私钥
            String key = (String) pri.get("key");
            // 通过RSAUtil 进行私钥解密
            password = RsaUtil.privateDecrypt(password, RsaUtil.getPrivateKey(key));
        } catch (Exception e) {
            throw new PublicKeyIsErrorException(SystemExceptionEnum.PUBLIC_KEY_ERROR_EXCEPTION);
        }
        return password;
    }
}
