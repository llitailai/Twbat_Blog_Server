package com.twbat.blog.common.util.util.email;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/17 - 20:48
 * @desciption
 * 邮件配置
 */
public class EmailConfig {

    /**
     * 主题
     */
    public static final String SUBJECT = "TWBAT博客验证码";

    /**
     * 模板路径
     */
    public static final String TEMPLATE = "EmailTemplate";

    /**
     * 发送邮件邮箱
     */
    public static final String SEND_EMAIL = "twbat_lee@163.com";

    /**
     * 验证码长度
     */
    public static final int CODE_LENGTH = 8;
}
