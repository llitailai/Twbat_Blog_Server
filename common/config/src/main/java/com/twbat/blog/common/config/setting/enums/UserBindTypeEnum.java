package com.twbat.blog.common.config.setting.enums;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/17 - 21:32
 * @desciption
 * 用户绑定类型枚举
 */
public enum UserBindTypeEnum {
    /**
     * 电话
     */
    TEL,
    /**
     * 邮箱
     */
    EMAIL,
    /**
     * QQ
     */
    QQ,
    /**
     * 微信
     */
    WECHAT;

    /**
     * 获取值
     * @return 值
     */
    public int getValue() {
        return this.ordinal();
    }
}
