package com.twbat.blog.center.permission.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author darkltl
 * @className SystemUserRolePO
 * @email darkltl@163.com
 * @date 2021/7/22 - 15:27
 * @description 系统用户角色表实体
 */
@Data
@TableName("system_user_role")
public class SystemUserRolePO {

    /**
     * 用户角色ID
     */
    @TableId("user_role_id")
    private Long userRoleId;

    /**
     * 角色ID
     */
    @TableField("role_id")
    private Integer roleId;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private Integer createUser;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 修改人
     */
    private Integer updateUser;

}
