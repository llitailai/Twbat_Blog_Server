package com.twbat.blog.common.config.define;

import lombok.Data;

import java.util.Date;

/**
 * @author darkltl
 * @className BasePO
 * @email darkltl@163.com
 * @date 2021/7/22 - 14:44
 * @description 基本PO实体类
 */
@Data
public class BasePO {

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人ID
     */
    private Integer createUser;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 修改人ID
     */
    private Integer updateUser;

}
