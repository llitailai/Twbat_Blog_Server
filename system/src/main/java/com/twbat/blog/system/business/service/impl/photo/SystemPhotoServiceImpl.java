package com.twbat.blog.system.business.service.impl.photo;

import cn.twbat.logger.core.factory.LoggerFactory;
import cn.twbat.logger.core.log.Logger;
import com.alibaba.fastjson.JSONObject;
import com.twbat.blog.center.permission.config.ServerConfig;
import com.twbat.blog.common.util.util.DateUtils;
import com.twbat.blog.common.util.util.config.ProjectConfig;
import com.twbat.blog.common.util.util.file.FileUploadUtil;
import com.twbat.blog.system.business.domain.photo.po.SystemPhotoPo;
import com.twbat.blog.system.business.repository.photo.SystemPhotoMapper;
import com.twbat.blog.system.business.service.photo.feign.SystemPhotoService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/10 - 17:55
 * @desciption
 * 系统照片服务实现类
 * 对于系统照片增删改查等基础功能提供实现
 */
@Service
public class SystemPhotoServiceImpl implements SystemPhotoService {

    /**
     * LOG日志,用于该实现类中日志的记录
     * 注意 该 Logger 和 LoggerFactory 是由logger模块提供 非logger4j
     */
    private static final Logger LOG = LoggerFactory.getLogger(SystemPhotoServiceImpl.class);

    /**
     * 系统照片数据库访问层,用于提供对于写入,写出持久化保存提供支持
     */
    @Resource
    private SystemPhotoMapper systemPhotoMapper;

    /**
     * 服务配置项 用于获取浏览器携带的访问路径
     */
    @Resource
    private ServerConfig serverConfig;

    @Override
    public JSONObject upload(MultipartFile file) {
        String url = null;

        try {
            url = FileUploadUtil.upload(ProjectConfig.getAvatarPath(), file);
            LOG.info("上传文件路径 URL: {},上传时间:{}",url,DateUtils.getTime());
        } catch (IOException e) {
            LOG.error("上传文件出现异常:{},异常时间:{}",e,DateUtils.getTime());
        }
        /*
            创建JSONObject 用于回显前端文件url
         */
        JSONObject res = new JSONObject();
        String resUrl = url;
        res.put("url", serverConfig.getUrl() + resUrl);

        /*
            插入数据库
         */
        SystemPhotoPo systemPhotoPo = new SystemPhotoPo();
        systemPhotoPo.setPhotoType(0);
        // 断言url不会为null,因为需要通过url根据/截取出文件名字
        assert url != null;
        String[] split = url.split("/");
        // 设置照片名称
        systemPhotoPo.setPhotoName(split[split.length - 1]);
        // 设置照片路径
        systemPhotoPo.setPhotoUrl(resUrl);
        // 设置上传时间
        systemPhotoPo.setUploadTime(DateUtils.getNowDate());
        // 设置床架时间
        systemPhotoPo.setCreateTime(DateUtils.getNowDate());
        // 插入数据库
        int rows = systemPhotoMapper.insert(systemPhotoPo);
        LOG.info("图片插入数据库完成,影响行数: {},时间: {}",rows,DateUtils.getTime());
        return res;
    }


}
