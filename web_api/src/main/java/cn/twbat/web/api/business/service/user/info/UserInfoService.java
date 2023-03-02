package cn.twbat.web.api.business.service.user.info;

import cn.twbat.web.api.business.domain.user.info.dto.SaveWebBlogUserInfoDto;
import cn.twbat.web.api.business.domain.user.info.po.WebBlogUserInfoPo;
import cn.twbat.web.api.business.domain.user.info.vo.WebBlogUserInfoVo;
import cn.twbat.web.api.business.service.user.info.component.UserInfoBasicComponent;
import com.twbat.blog.center.permission.global.exception.enums.BusinessExceptionEnum;
import com.twbat.blog.common.util.util.ApiResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/10 - 15:00
 * @desciption 用户信息服务
 */
@Service
public class UserInfoService {

    /**
     * 用户信息基础功能组件
     */
    @Resource
    private UserInfoBasicComponent userInfoBasicComponent;

    /**
     * 获取用户信息,根据用户ID
     *
     * @param userId 用户ID
     * @return 全局统一返回对象
     */
    public ApiResult getUserInfo(Integer userId) {
        WebBlogUserInfoPo userInfo = userInfoBasicComponent.getUserInfoByUserId(userId);
        // 如果用户信息为空,则通知前端弹出填写用户信息页面
        if (userInfo == null) {
            return ApiResult.builder()
                    .code(BusinessExceptionEnum.USER_INFO_IS_NULL.getCode())
                    .data(WebBlogUserInfoVo.getEmptyVo())
                    .msg("")
                    .build();
        }
        return ApiResult.success(userInfoBasicComponent.getUserInfoVo());
    }

    public ApiResult saveUserInfo(SaveWebBlogUserInfoDto dto, Integer userId) {
        return ApiResult.success(userInfoBasicComponent.saveUserInfo(dto, userId));
    }
}
