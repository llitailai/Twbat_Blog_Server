package cn.twbat.web.api.function.controller.info;

import cn.twbat.web.api.business.domain.user.info.dto.SaveWebBlogUserInfoDto;
import cn.twbat.web.api.business.service.user.info.UserInfoService;
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
 * @date 2022/2/10 - 15:16
 * @desciption
 * 用户信息控制器
 */
@RestController
public class UserInfoController extends ApiController {

    /**
     * 用户信息服务 , 用于提供用户信息操作的服务支持
     */
    @Resource
    private UserInfoService userInfoService;

    /**
     * 获取用户信息
     * @return 全局统一返回对象
     */
    @GetMapping("/user/getInfo")
    @Required
    public ApiResult<?> getUserInfo() {
        return userInfoService.getUserInfo(getCurrentUser().getUserId());
    }

    /**
     * 保存用户信息
     * @param dto 保存用户信息dto
     * @return 全局统一返回对象
     */
    @PostMapping("/user/saveInfo")
    @Required
    @RequiredValidParam
    public ApiResult<?> saveUserInfo(@Valid SaveWebBlogUserInfoDto dto) {
        return userInfoService.saveUserInfo(dto, getCurrentUser().getUserId());
    }
}
