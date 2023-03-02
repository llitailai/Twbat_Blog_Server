package com.twbat.blog.system.controller.register;

import com.twbat.blog.common.util.util.ApiResult;
import com.twbat.blog.common.web.valid.annotations.RequiredValidParam;
import com.twbat.blog.common.web.valid.annotations.Valid;
import com.twbat.blog.system.base.BaseController;
import com.twbat.blog.system.business.domain.user.dto.user.register.RegisterSystemUserDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author darkltl
 * @date 2021-09-10 15:56
 * 系统用户注册控制器
 */
@RestController
public class SystemUserRegisterController extends BaseController {


    /**
     * todo: 后期完成 系统用户注册控制器
     * @param registerSystemUserDto 系统用户注册dto
     * @return 全局统一返回对象
     */
    @PostMapping("/register")
    @RequiredValidParam
    @SuppressWarnings("all")
    public ApiResult<?> registerController(@Valid RegisterSystemUserDto registerSystemUserDto) {
        return null;
    }
}
