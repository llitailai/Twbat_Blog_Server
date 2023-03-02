package cn.twbat.web.api.business.domain.user.dto;

import cn.twbat.web.api.business.service.settings.feign.SettingsFeignService;
import com.alibaba.fastjson.JSONObject;
import com.twbat.blog.common.config.setting.util.RsaUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/1/7 - 10:57
 * @desciption 用户登录数据传输对象
 */
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserLoginDto {

    /**
     * 用户登录账户
     */
    private String account;

    /**
     * 用户登录密码
     */
    private String password;

}
