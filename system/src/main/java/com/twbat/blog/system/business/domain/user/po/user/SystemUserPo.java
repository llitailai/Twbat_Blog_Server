package com.twbat.blog.system.business.domain.user.po.user;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author darkltl
 * @className UserPo
 * @email darkltl@163.com
 * @date 2021/8/16 - 13:20
 * @description
 */
@Data
@Accessors(chain = true)
@TableName("system_user")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SystemUserPo implements Serializable {

    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    @TableField("user_tel")
    private String userTel;

    @TableField("user_password")
    private String userPassword;

    @TableField("status")
    private Integer status;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(value = "create_user")
    private Integer createUser;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableField(value = "update_user")
    private Integer updateUser;

}
