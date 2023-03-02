package com.twbat.blog.common.util.util.email;

import com.twbat.blog.common.util.util.RandomUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/17 - 20:46
 * @desciption
 */
@Component
public class SendEmailUtil {

    /**
     * 邮件工具,用于发送邮件
     */
    @Resource
    private EmailUtils emailUtils;

    /**
     * 发送邮件
     * @param to 收件人邮箱
     * @return 验证码
     * @throws MessagingException MessagingException
     */
    public void sendEmails(String to,String code) throws MessagingException {
        Map<String,Object> param = new HashMap<>();
        param.put("params",code);
        emailUtils.sendThymeleafMail(
                EmailConfig.SUBJECT,
                EmailConfig.SEND_EMAIL,
                to,
                null,
                null,
                param,
                EmailConfig.TEMPLATE
        );
    }
}
