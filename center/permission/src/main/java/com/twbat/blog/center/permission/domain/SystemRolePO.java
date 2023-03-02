package com.twbat.blog.center.permission.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author darkltl
 * @className SystemRoleRepository
 * @email darkltl@163.com
 * @date 2021/7/22 - 15:45
 * @description 系统角色表实体
 */
@Data
@TableName("system_role")
public class SystemRolePO {

    /**
     * 角色ID
     */
    @TableId("role_id")
    private Integer roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 权限代码
     */
    private String permissionCode;

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
