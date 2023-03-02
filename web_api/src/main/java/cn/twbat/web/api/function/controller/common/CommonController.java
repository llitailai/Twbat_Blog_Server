package cn.twbat.web.api.function.controller.common;

import cn.twbat.web.api.business.service.common.feign.CommonUploadFeignService;
import cn.twbat.web.api.global.ApiController;
import com.twbat.blog.common.util.util.ApiResult;
import com.twbat.blog.common.util.util.auth.annotaions.Required;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/10 - 22:26
 * @desciption 公共接口控制器
 */
@RestController
public class CommonController extends ApiController {

    /**
     * 公共上传Feign调用服务
     */
    @Resource
    private CommonUploadFeignService commonUploadFeignService;

    /**
     * 上传文件
     * @param file 文件
     * @return 全局统一返回对象
     */
    @PostMapping("/common/uploadFile")
    @Required
    public ApiResult<?> uploadFile(MultipartFile file) {
        return ApiResult.success(commonUploadFeignService.upload(file));
    }
}
