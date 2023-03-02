package cn.twbat.web.api.function.controller.user;

import cn.twbat.web.api.business.domain.user.dto.RegisterUserDto;
import cn.twbat.web.api.business.domain.user.dto.UserLoginDto;
import cn.twbat.web.api.business.domain.user.dto.VerificationCodeDto;
import cn.twbat.web.api.business.service.settings.feign.SettingsFeignService;
import cn.twbat.web.api.business.service.user.UserService;
import cn.twbat.web.api.global.ApiController;
import com.twbat.blog.common.util.util.ApiResult;
import com.twbat.blog.common.util.util.auth.annotaions.Required;
import com.twbat.blog.common.web.valid.annotations.RequiredValidParam;
import com.twbat.blog.common.web.valid.annotations.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/1/7 - 10:53
 * @desciption 用户操作接口
 */
@RestController
public class UserOperateController extends ApiController {

    /**
     * 用户服务 对用户操作提供服务支持
     */
    @Resource
    private UserService userService;

    /**
     * 用户登录
     * @param userLoginDto 用户登录参数dto
     * @return 全局统一返回对象
     */
    @PostMapping("/user/operate/login")
    public ApiResult<?> userLoginOperate(UserLoginDto userLoginDto) {
        return userService.userLoginService(userLoginDto);
    }

    /**
     * 用户登出
     * @return 全局统一返回对象
     */
    @Required
    @PostMapping("/user/operate/logout")
    public ApiResult<?> logOut() {
        return userService.logout();
    }


    /**
     * 通过调用该接口来完成用户登录信息的记录
     * 拦截器实现,拦截该接口并处理相应操作
     * @return 全局统一返回对象
     */
    @PostMapping("/user/operate/record")
    @Required
    public ApiResult<?> record() {
        return ApiResult.success();
    }

    /**
     * todo: 二期开发
     *
     * 获取用户信息
     *
     * @return 全局统一返回对象
     */
    @Required
    @GetMapping("/user/operate/getUserInfo")
    public ApiResult<?> getUserInfo() {
        return ApiResult.success();
    }


    /**
     * web端用户注册
     * @param dto 注册用户参数dto
     * @return 全局统一返回对象
     */
    @PostMapping("/user/operate/register")
    @RequiredValidParam
    public ApiResult<?> registerUser(@Valid RegisterUserDto dto) {
        return userService.registerUser(dto);
    }

    /**
     * 发送注册邮件
     * @param email 邮箱
     * @return 全局统一返回对象
     */
    @PostMapping("/user/operate/sendRegisterEmail")
    @RequiredValidParam
    public ApiResult<?> sendRegisterEmail(@Valid(email = true) String email) {
        return userService.sendRegisterEmail(email);
    }

    /**
     * 校验验证码
     * @param dto 用户校验验证码参数dto
     * @return 全局统一返回对象
     */
    @RequiredValidParam
    @PostMapping("/user/operate/verificationCode")
    public ApiResult<?> verificationCode(@Valid VerificationCodeDto dto) {
        return userService.verificationCode(dto);
    }
}
