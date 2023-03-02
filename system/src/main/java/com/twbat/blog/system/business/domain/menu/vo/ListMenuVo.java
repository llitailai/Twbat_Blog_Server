package com.twbat.blog.system.business.domain.menu.vo;

import lombok.Data;

/**
 * @author darkltl
 * @date 2021-09-14 12:38
 * 菜单列表数据Vo
 */
@Data
public class ListMenuVo {
    /**
     * 菜单ID
     */
    private Integer menuId;

    /**
     * 父级菜单ID
     */
    private Integer parentId;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 菜单图标
     */
    private String menuIcon;

    /**
     * 菜单路径
     */
    private String menuUrl;

    /**
     * 菜单权限
     */
    private String menuPermission;

    /**
     * 菜单类型
     */
    private Integer menuType;

    /**
     * 菜单排序
     */
    private Integer menuSort;

    /**
     * 父级名称
     */
    private String parentName;

}
