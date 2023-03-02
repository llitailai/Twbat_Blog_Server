package cn.twbat.web.api.business.service.user;

import cn.twbat.web.api.business.domain.user.dto.RegisterUserDto;
import cn.twbat.web.api.business.domain.user.dto.UserLoginDto;
import cn.twbat.web.api.business.domain.user.dto.VerificationCodeDto;
import cn.twbat.web.api.business.service.user.component.UserBasicComponent;
import cn.twbat.web.api.business.service.user.component.UserRegisterComponent;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.twbat.blog.center.permission.global.exception.business.user.UserNotExistException;
import com.twbat.blog.center.permission.global.exception.business.user.UserPasswordErrorException;
import com.twbat.blog.center.permission.global.exception.enums.BusinessExceptionEnum;
import com.twbat.blog.center.permission.global.exception.enums.SystemExceptionEnum;
import com.twbat.blog.center.permission.global.exception.system.PublicKeyIsErrorException;
import com.twbat.blog.center.permission.global.web.JwtUtil;
import com.twbat.blog.common.util.util.ApiResult;
import com.twbat.blog.common.util.util.auth.Token;
import com.twbat.blog.common.web.valid.util.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/9 - 20:59
 * @desciption 用户服务
 */
@Service
public class UserService {

    /**
     * 用户基础功能组件
     */
    @Resource
    private UserBasicComponent userBasicComponent;

    /**
     * 用户注册功能组件
     */
    @Resource
    private UserRegisterComponent userRegisterComponent;

    @Resource
    private UserPasswordEncryptService userPasswordEncryptService;

    /**
     * 用户登录服务
     * @param dto 登录参数dto
     * @return token
     * @exception PublicKeyIsErrorException 公钥错误时会抛出该异常
     * @exception UserNotExistException 用户不存在时抛出该异常
     * @exception UserPasswordErrorException 用户密码错误时抛出该异常
     */
    public ApiResult<?> userLoginService(UserLoginDto dto) {
        // 判断用户是否存在
        if (!userBasicComponent.userExist(dto.getAccount())) {
            throw new UserNotExistException(BusinessExceptionEnum.USER_NOT_EXIST_EXCEPTION);
        }

        String password = userPasswordEncryptService.convertPassword(dto.getPassword());
        // 判断用户密码是否错误
        if (!userBasicComponent.passwordIsSuccess(dto.getAccount(), password)) {
            throw new UserPasswordErrorException(BusinessExceptionEnum.USER_NOT_EXIST_EXCEPTION);
        }

        // 生成token并返回
        String token = userBasicComponent.getToken();
        JSONObject res = new JSONObject();
        res.put("token", token);
        return ApiResult.success(res);
    }

    /**
     * 登出功能
     * @return 全局统一返回对象
     */
    public ApiResult<?> logout() {
        userBasicComponent.logOut(JwtUtil.getToken());
        return ApiResult.success();
    }


    /**
     * 注册用户
     * @param dto 注册用户参数dto
     * @return 全局统一返回对象
     */
    public ApiResult<?> registerUser(RegisterUserDto dto) {
        userRegisterComponent.registerUser(dto,userPasswordEncryptService);
        return ApiResult.success();
    }

    /**
     * 发送注册邮件
     * @param email 邮箱
     * @return 全局统一返回对象
     */
    public ApiResult<?> sendRegisterEmail(String email) {
        return userRegisterComponent.sendRegisterEmail(email);
    }

    /**
     * 校验验证码
     * @param dto 用户校验验证码参数dto
     * @return 全局统一返回对象
     */
    public ApiResult<?> verificationCode(VerificationCodeDto dto) {
        return userRegisterComponent.verificationCode(dto.getEmail(), dto.getCode());
    }
}
