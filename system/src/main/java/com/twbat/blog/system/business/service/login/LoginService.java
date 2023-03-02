package com.twbat.blog.system.business.service.login;

import com.twbat.blog.common.util.util.ApiResult;
import com.twbat.blog.common.util.util.auth.Token;
import com.twbat.blog.system.business.domain.user.dto.user.login.SystemLoginDto;
import com.twbat.blog.system.business.service.login.component.record.LoginRecordComponent;
import com.twbat.blog.system.business.service.login.component.valid.UserLoginValidComponent;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @author darkltl
 * @date 2021-09-13 16:42
 * 登录服务
 */
@Service
public class LoginService {


    /**
     * 用户登录校验组件
     */
    @Resource
    private UserLoginValidComponent userLoginValidComponent;

    /**
     * 用户登录信息记录组件
     */
    @Resource
    private LoginRecordComponent loginRecordComponent;


    /**
     * 用户登录服务
     * @param loginDto 登录参数dto
     * @return 全局统一返回对象
     */
    public ApiResult<?> loginService(SystemLoginDto loginDto) {
        userLoginValidComponent.valid(loginDto.getPhone(), loginDto.getPassword());
        // 如果未出现异常,则记录用户登录时间
        loginRecordComponent.recordLogin(userLoginValidComponent.getLoginUserId());
        // 生成Token
        String token = Token.createToken(userLoginValidComponent.getLoginUserId(), loginDto.getPassword());
        HashMap<String, Object> res = new HashMap<>(1);
        res.put("token", token);
        return ApiResult.success(res);
    }


}
