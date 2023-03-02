package com.twbat.blog.system.business.domain.role.relation;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author darkltl
 * @date 2021-09-22 11:17
 * 系统角色菜单关联关系表实体
 */
@Data
@Accessors(chain = true)
@TableName("system_role_menu")
public class SystemRoleMenuRelation {

    /**
     * 系统角色菜单关联关系表主键
     */
    @TableId(value = "rm_id", type = IdType.AUTO)
    private Long rmId;

    /**
     * 系统角色ID
     */
    @TableField("role_id")
    private Integer roleId;

    /**
     * 系统菜单ID
     */
    @TableField("menu_id")
    private Integer menuId;

}
