package com.twbat.blog.system.business.service.photo.feign;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.multipart.MultipartFile;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/10 - 17:53
 * @desciption
 * 系统照片存储服务,用于提供对服务增,删,改,查提供支持
 */
public interface SystemPhotoService {
    /**
     * 上传图片并返回图片URL
     *
     * @param multipartFile 文件
     * @return 存储图片url的JSONObject
     */
    JSONObject upload(MultipartFile multipartFile);
}
