package com.twbat.blog.center.permission.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.twbat.blog.center.permission.util.SensitiveInformationHandleUtil;
import lombok.Data;

import java.util.Date;

/**
 * @author darkltl
 * @className SystemPermissionPO
 * @email darkltl@163.com
 * @date 2021/7/22 - 13:59
 * @description 系统权限PO对象
 */
@Data
@TableName("system_permission")
public final class SystemPermissionPO {

    /**
     * 权限ID
     */
    @TableId("permission_id")
    private Integer permissionId;

    /**
     * 权限代码
     */
    @TableField("permission_code")
    private String permissionCode;

    /**
     * 权限名称
     */
    @TableField("permission_name")
    private String permissionName;

    /**
     * 权限行为代码
     */
    @TableField("behavior_code")
    private String behaviorCode;

    /**
     * 菜单ID
     */
    @TableField("menu_Id")
    private Integer menuId;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 创建人ID
     */
    @TableField("create_user")
    private Integer createUser;

    /**
     * 修改时间
     */
    @TableField("update_time")
    private Date updateTime;

    /**
     * 修改人ID
     */
    @TableField("update_user")
    private Integer updateUser;


    @Override
    public String toString() {
        return "SystemPermissionPO{" +
                "permissionId=" + getPermissionId() +
                ", permissionCode='" + SensitiveInformationHandleUtil.handle(getPermissionCode()) + '\'' +
                ", permissionName='" + SensitiveInformationHandleUtil.handle(getPermissionName()) + '\'' +
                ", behaviorCode='" + SensitiveInformationHandleUtil.handle(getBehaviorCode()) + '\'' +
                ", menuId=" + getMenuId() +
                ", createTime=" + getCreateTime() +
                ", createUser=" + getCreateUser() +
                ", updateTime=" + getUpdateTime() +
                ", updateUser=" + getUpdateUser() +
                '}';
    }
}
