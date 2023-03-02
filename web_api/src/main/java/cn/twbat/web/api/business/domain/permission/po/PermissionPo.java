package cn.twbat.web.api.business.domain.permission.po;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/18 - 10:38
 * @desciption
 * 权限PO
 */
public class PermissionPo {

    /**
     * 权限名称
     */
    public String permissionName;

    /**
     * 权限代码
     */
    private String permissionCode;

    /**
     * 权限错误名称
     */
    private String errorMsg;

    /**
     * 权限类型
     * 如权限类型未博客编辑页面
     * 则管理编辑按钮等权限名称
     */
    private String permissionType;
}
