package cn.twbat.web.api.function.controller.permission;

import cn.twbat.web.api.business.service.permissions.PermissionComponent;
import cn.twbat.web.api.global.ApiController;
import com.twbat.blog.common.util.util.ApiResult;
import com.twbat.blog.common.util.util.auth.annotaions.Required;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/26 - 15:37
 * @desciption
 * 权限控制器
 */
@RestController
public class PermissionController extends ApiController {

    @Resource
    private PermissionComponent permissionComponent;

    @GetMapping("/permission")
    @Required
    public ApiResult<?> permission(@RequestParam("key") String serviceKey, @RequestParam("executeKey") String methodKey, @RequestParam("params") Object ... params) {
        permissionComponent.permission(serviceKey,methodKey,params);
        return ApiResult.success();
    }

}
