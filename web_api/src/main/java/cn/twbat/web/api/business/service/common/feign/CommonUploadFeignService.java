package cn.twbat.web.api.business.service.common.feign;

import com.alibaba.fastjson.JSONObject;
import com.twbat.blog.common.config.setting.constant.Address;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/10 - 22:23
 * @desciption 公共上传Feign调用服务
 */
@FeignClient(url = "${setting-url}", name = "commonUploadUrl")
public interface CommonUploadFeignService {

    /**
     * 上传图片
     *
     * @param file 文件
     * @return URL
     */
    @PostMapping(value = Address.COMMON_UPLOAD_FEIGN_URL, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    JSONObject upload(@RequestPart("file") MultipartFile file);
}
