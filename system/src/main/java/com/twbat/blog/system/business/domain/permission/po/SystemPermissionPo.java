package com.twbat.blog.system.business.domain.permission.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * @author darkltl
 * @date 2021-09-14 13:00
 * 系统权限Po
 */
@Data
@TableName("system_permission")
public class SystemPermissionPo {

    /**
     * 权限ID
     */
    @TableId(value = "permission_id", type = IdType.AUTO)
    private Integer permissionId;

    /**
     * 父级权限ID
     */
    @TableField("parent_id")
    private Integer parentId;

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
     * 行为标识码
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
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 创建人
     */
    @TableField(value = "create_user")
    private Integer createUser;

    /**
     * 修改时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 修改人
     */
    @TableField("update_user")
    private Integer updateUser;
}
