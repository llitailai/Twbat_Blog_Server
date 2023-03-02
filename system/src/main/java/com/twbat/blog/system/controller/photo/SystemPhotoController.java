package com.twbat.blog.system.controller.photo;

import com.alibaba.fastjson.JSONObject;
import com.twbat.blog.common.web.valid.annotations.RequiredValidParam;
import com.twbat.blog.common.web.valid.annotations.Valid;
import com.twbat.blog.system.base.BaseController;
import com.twbat.blog.system.business.service.photo.feign.SystemPhotoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/10 - 21:19
 * @desciption
 * 服务端系统存储照片控制器
 */
@RestController
public class SystemPhotoController extends BaseController {

    /**
     * 系统照片存储服务,用于提供对服务增,删,改,查提供支持
     */
    @Resource
    private SystemPhotoService systemPhotoService;

    /**
     * 上传文件并返回url
     * @param file 文件
     * @return 存储图片url的JSONObject
     */
    @PostMapping("/upload")
    @RequiredValidParam
    public JSONObject uploadFile(@Valid MultipartFile file) {
        return systemPhotoService.upload(file);
    }
}
