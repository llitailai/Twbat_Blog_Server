package cn.twbat.web.api.user;

import cn.twbat.web.api.WebApiApplication;
import com.twbat.blog.common.util.util.email.EmailUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/17 - 15:11
 * @desciption
 * 测试发送邮件
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebApiApplication.class)
public class TestSendEmail {

    @Resource
    private EmailUtils emailUtils;

    @Test
    public void testSendEmail() throws MessagingException {

        URL resource = this.getClass().getClassLoader().getResource("templates/EmailTemplate.html");
        System.out.println(resource);
        Map<String,Object> data = new HashMap<>();
        data.put("params","102432");
        String[] cc = {};
        String[] bcc = {};
        emailUtils.sendThymeleafMail(
                "TWBAT博客验证码",
                "twbat_lee@163.com",
                "darkltl@163.com",
                cc,
                bcc,
                data,
                "EmailTemplate"

        );
    }
}
