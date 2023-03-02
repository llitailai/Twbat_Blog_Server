package com.twbat.blog.system.business.domain.user.dto.user.register;

import cn.twbat.logger.core.log.util.SensitiveInformationHandleUtil;
import com.twbat.blog.common.web.valid.annotations.Valid;
import lombok.Data;

/**
 * @author darkltl
 * @date 2021-09-08 16:34
 * @description 注册系统用户数据传输对象
 */
@Data
public class RegisterSystemUserDto {

    /**
     * 用户手机号
     */
    @Valid(tel = true)
    private String userTel;

    /**
     * 用户密码
     */
    @Valid(password = true)
    private String userPassword;

    @Override
    public String toString() {
        return "RegisterSystemUserDto{" +
                "userTel='" + SensitiveInformationHandleUtil.handle(userTel) + '\'' +
                ", userPassword='" + SensitiveInformationHandleUtil.handle(userPassword) + '\'' +
                '}';
    }
}
