package com.twbat.blog.system.business.domain.role.po;

import lombok.Data;

import java.util.Date;

/**
 * @author darkltl
 * @date 2021-09-14 15:26
 * 系统角色po
 */
@Data
public class SystemRolePo {

    /**
     * 角色ID
     */
    private Integer roleId;

    /**
     * 角色名称
     */
    private String roleName;

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
