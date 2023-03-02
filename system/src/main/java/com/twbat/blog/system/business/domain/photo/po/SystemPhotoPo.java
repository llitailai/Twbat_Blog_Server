package com.twbat.blog.system.business.domain.photo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/10 - 17:48
 * @desciption 系统照片存储PO
 */
@Data
@TableName("system_photo")
public class SystemPhotoPo {

    /**
     * 照片ID
     */
    @TableId(value = "photo_id", type = IdType.AUTO)
    private Integer photoId;

    /**
     * 照片名称
     */
    @TableField("photo_name")
    private String photoName;

    /**
     * 照片地址
     */
    @TableField("photo_url")
    private String photoUrl;

    /**
     * 照片类型
     */
    @TableField("photo_type")
    private Integer photoType;

    /**
     * 上传时间
     */
    @TableField("upload_time")
    private Date uploadTime;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
}
