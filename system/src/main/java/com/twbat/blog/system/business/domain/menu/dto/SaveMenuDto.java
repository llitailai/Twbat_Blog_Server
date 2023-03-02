package com.twbat.blog.system.business.domain.menu.dto;

import lombok.Data;

/**
 * @author darkltl
 * @date 2021-09-14 14:45
 * 添加菜单Dto
 */
@Data
public class SaveMenuDto {

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 父级菜单ID
     */
    private Integer parentId;

    /**
     * 菜单url
     */
    private String menuUrl;

    /**
     * 菜单权限
     */
    private String menuPermission;

    /**
     * 菜单排序
     */
    private Integer menuSort;

    /**
     * 菜单Icon
     */
    private String menuIcon;
}
