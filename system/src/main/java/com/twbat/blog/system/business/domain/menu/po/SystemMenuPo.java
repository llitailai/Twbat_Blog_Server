package com.twbat.blog.system.business.domain.menu.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * @author darkltl
 * @date 2021-09-14 10:20
 * 系统菜单Po
 */
@Data
@TableName("system_menu")
public class SystemMenuPo {

    /**
     * 菜单主键
     */
    @TableId(value = "menu_id", type = IdType.AUTO)
    private Integer menuId;

    /**
     * 父级菜单ID
     */
    @TableField("parent_id")
    private Integer parentId;

    /**
     * 菜单名称
     */
    @TableField("menu_name")
    private String menuName;

    /**
     * 菜单图标
     */
    @TableField("menu_icon")
    private String menuIcon;

    /**
     * 菜单类型
     * 0 目录
     * 1 菜单
     * 2 按钮
     */
    @TableField("menu_type")
    private Integer menuType;

    /**
     * 菜单排序
     */
    @TableField("menu_sort")
    private Integer menuSort;

    /**
     * 组件名称
     */
    @TableField("menu_component")
    private String menuComponent;

    /**
     * 菜单权限
     */
    @TableField("menu_permission")
    private String menuPermission;

    /**
     * 组件地址
     */
    @TableField("menu_url")
    private String menuUrl;

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
    @TableField(value = "update_user")
    private Integer updateUser;
}
