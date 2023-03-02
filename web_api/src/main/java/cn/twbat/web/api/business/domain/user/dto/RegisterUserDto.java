package cn.twbat.web.api.business.domain.user.dto;

import com.twbat.blog.common.web.valid.annotations.Valid;
import lombok.Data;


/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/17 - 19:13
 * @desciption
 * 注册用户参数DTO
 */
@Data
public class RegisterUserDto {


    /**
     * 注册账号
     */
    private String account;

    /**
     * 邮箱
     */
    @Valid(email = true)
    private String email;


    /**
     * 密码
     */
    @Valid
    private String password;

    /**
     * 重复密码
     */
    @Valid
    private String repPassword;

}
