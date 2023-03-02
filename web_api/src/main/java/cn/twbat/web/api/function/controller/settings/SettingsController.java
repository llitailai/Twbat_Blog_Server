package cn.twbat.web.api.function.controller.settings;

import cn.twbat.web.api.business.service.settings.feign.SettingsFeignService;
import cn.twbat.web.api.global.ApiController;
import com.twbat.blog.common.util.util.ApiResult;
import com.twbat.blog.common.util.util.auth.annotaions.Required;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/9 - 18:14
 * @desciption
 * 系统设置控制器
 */
@RestController
public class SettingsController extends ApiController {

    /**
     * 系统设置对外调用服务定义类
     */
    @Resource
    private SettingsFeignService settingsFeignService;

    /**
     * 获取登录密码加密公钥
     * @return 全局统一返回对象
     */
    @GetMapping("/setting/getPubKey")
    public ApiResult<?> getLoginPasswordPublicKey() {
        return ApiResult.success(settingsFeignService.getLoginPasswordPublicKey());
    }
}
