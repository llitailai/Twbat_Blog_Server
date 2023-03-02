package com.twbat.blog.center.permission.domain.ip;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author darkltl
 * @version jdk1.8
 * @email darkltl@163.com
 * @date 2021/9/11 14:21
 * @description
 * @since 1.0
 * ip地址记录Po
 */
@Data
@TableName("system_ip_record")
public class IpRecordPo implements Serializable {

    /**
     * 系统访问IP统计表ID
     */
    @TableId(value = "ip_id", type = IdType.AUTO)
    private Long ipId;

    /**
     * 访问IP
     */
    @TableField("ip")
    private String ip;

    /**
     * 访问时间
     */
    @TableField("visit_time")
    private Date visitTime;

    /**
     * 访问函数
     */
    @TableField("visit_method")
    private String visitMethod;

    /**
     * 访问请求方法
     */
    @TableField("visit_request_mehotd")
    private String visitRequestMethod;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private String createTime;

    /**
     * 创建人{创建线程名称+创建线程ID}
     */
    @TableField("create_user")
    private String createUser;
}
