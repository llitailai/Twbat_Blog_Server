package com.twbat.blog.system.business.domain.menu.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author darkltl
 * @date 2021-09-14 11:41
 * 菜单Vo
 */
@Data
public class MenuVo {

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
     * 菜单子节点
     */
    private List<MenuVo> children = new ArrayList<>();

}
