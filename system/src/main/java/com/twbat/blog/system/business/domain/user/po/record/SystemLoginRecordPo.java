package com.twbat.blog.system.business.domain.user.po.record;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * @author darkltl
 * @date 2021-09-13 17:02
 * 系统用户登录日志记录PO
 */
@TableName("system_login_record")
@Data
public class SystemLoginRecordPo {

    /**
     * 用户登录日志记录ID
     */
    @TableId(value = "record_id", type = IdType.AUTO)
    private Long recordId;

    /**
     * 用户登录IP
     */
    @TableField("record_ip")
    private String recordIp;

    /**
     * 用户登录时间
     */
    @TableField("login_time")
    private Date loginTime;

    /**
     * 登录用户ID
     */
    @TableField("user_id")
    private Integer userId;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
}
