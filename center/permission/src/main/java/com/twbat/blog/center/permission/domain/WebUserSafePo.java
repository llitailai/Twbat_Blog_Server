package com.twbat.blog.center.permission.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/10 - 2:24
 * @desciption
 */
@TableName("user_safe")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class WebUserSafePo {

    /**
     * 用户安全表主键ID
     */
    @TableId(value = "safe_id", type = IdType.AUTO)
    private Long safeId;

    /**
     * 登录时间
     */
    @TableField("login_time")
    private Date loginTime;

    /**
     * 用户登录设备
     */
    @TableField("login_equipment")
    private String loginEquipment;

    /**
     * 用户登录IP
     */
    @TableField("login_ip")
    private String loginIp;

    /**
     * 用户登录城市
     */
    @TableField("login_city")
    private String loginCity;

    /**
     * 用户登录国家
     */
    @TableField("login_country")
    private String loginCountry;

    /**
     * web用户ID
     */
    @TableField("user_id")
    private Integer userId;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date creteTime;

    /**
     * 修改时间
     */
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private Date updateTime;
}
