package com.twbat.blog.system.business.domain.settings.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/1/11 - 11:24
 * @desciption 系统设置Po实体
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("system_settings")
public class SystemSettingsPo {

    /**
     * 系统设置表ID
     */
    @TableId(value = "setting_id", type = IdType.AUTO)
    private Integer settingId;

    /**
     * 系统设置名称
     */
    @TableField("setting_name")
    private String settingName;

    /**
     * 系统设置值
     */
    @TableField("setting_value")
    private String settingValue;

    /**
     * 系统代码
     */
    @TableField("setting_code")
    private String settingCode;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT)
    private Date updateTime;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 修改人
     */
    private String updateUser;
}
