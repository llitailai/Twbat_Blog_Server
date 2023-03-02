package com.twbat.blog.system.business.domain.user.dto.user.login;

import com.twbat.blog.common.web.valid.annotations.Valid;
import lombok.Data;

/**
 * @author darkltl
 * @date 2021-09-13 17:40
 * 用户登录Dto
 */
@Data
public class SystemLoginDto {

    /**
     * 系统用户登录电话
     */
    @Valid(tel = true)
    private String phone;

    /**
     * 系统用户登录密码
     */
    @Valid(password = true)
    private String password;

}
