package com.twbat.blog.common.util.util.email;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Date;
import java.util.Map;

/**
 * Copyright © ,2022-2033, Twbat.LiTaiLai, All Rights Reserved
 *
 * @author darkltl
 * @email <a href='darkltl@163.com'> </a>
 * @date 2022/2/17 - 14:46
 * @desciption
 * 邮件工具类
 */
@Component
public class EmailUtils {

    /**
     * Java邮件发送接口
     */
    @Resource
    private JavaMailSender javaMailSender;

    /**
     * 模板引擎
     */
    @Resource
    private TemplateEngine templateEngine;


    /**
     * 发送简单的文件邮件
     *
     * @param subject 主题
     * @param from    发件人
     * @param to      收件人
     * @param text    邮件的正文
     */
    @SuppressWarnings("all")
    public void sendTextMail(String subject, String from, String to, String text) {
        this.sendTextMail(subject, from, to, null, null, text);
    }

    /**
     * 发送简单的文件邮件
     *
     * @param subject 主题
     * @param from    发件人
     * @param to      收件人
     * @param cc      抄送人，可以有多个抄送人
     * @param bcc     隐秘抄送人，可以有多个
     * @param text    邮件的正文
     */
    @SuppressWarnings("all")
    public void sendTextMail(String subject, String from, String to, String[] cc, String[] bcc, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(subject);
        message.setFrom(from);
        message.setTo(to);
        if (cc != null) {
            message.setCc(cc);
        }
        if (bcc != null) {
            message.setBcc(bcc);
        }
        message.setSentDate(new Date());
        message.setText(text);
        javaMailSender.send(message);
    }

    /**
     * 发送带附件的邮件
     *
     * @param subject  主题
     * @param from     发件人
     * @param to       收件人
     * @param text     邮件的正文
     * @param filePath 附件
     */
    @SuppressWarnings("all")
    public void sendAttachFileMail(String subject, String from, String to, String text, String filePath) throws MessagingException {
        this.sendAttachFileMail(subject, from, to, null, null, text, filePath);
    }

    /**
     * 发送带附件的邮件
     *
     * @param subject  主题
     * @param from     发件人
     * @param to       收件人
     * @param cc       抄送人，可以有多个抄送人
     * @param bcc      隐秘抄送人，可以有多个
     * @param text     邮件的正文
     * @param filePath 附件
     */
    public void sendAttachFileMail(String subject, String from, String to, String[] cc, String[] bcc, String text, String filePath) throws MessagingException {
        MimeMessage mimeMessage = this.createMimeMessage();
        MimeMessageHelper helper = this.initMimeMessageHelper(mimeMessage, subject, from, to, cc, bcc);
        helper.setText(text);

        File file = new File(filePath);
        //4. 添加邮件附件
        helper.addAttachment(file.getName(), file);
        //5. 发送邮件
        javaMailSender.send(mimeMessage);
    }

    /**
     * 发送邮件使用Thymeleaf模板
     *
     * @param subject      主题
     * @param from         发件人
     * @param to           收件人
     * @param data         邮件模板需要替换的数据
     * @param templatePath 模板路径 路径在src/main/resources/templates/下
     * @throws MessagingException MessagingException
     */
    @SuppressWarnings("all")
    public void sendThymeleafMail(String subject, String from, String to, Map<String, Object> data, String templatePath) throws MessagingException {
        this.sendThymeleafMail(subject, from, to, null, null, data, templatePath);
    }

    /**
     * 发送邮件使用Thymeleaf模板
     *
     * @param subject      主题
     * @param from         发件人
     * @param to           收件人
     * @param cc           抄送人，可以有多个抄送人
     * @param bcc          隐秘抄送人，可以有多个
     * @param data         邮件模板需要替换的数据
     * @param templatePath 模板路径 路径在src/main/resources/templates/下
     * @throws MessagingException MessagingException
     */
    public void sendThymeleafMail(String subject, String from, String to, String[] cc, String[] bcc, Map<String, Object> data, String templatePath) throws MessagingException {
        //1. 构建邮件对象，注意，这里要通过 javaMailSender 来获取一个复杂邮件对象
        MimeMessage mimeMessage = this.createMimeMessage();
        MimeMessageHelper helper = this.initMimeMessageHelper(mimeMessage, subject, from, to, cc, bcc);

        Context context = new Context();
        if (data != null) {
            data.forEach(context::setVariable);
        }
        String process = templateEngine.process(templatePath, context);
        helper.setText(process, true);

        mimeMessage.setHeader("X-Priority", "3");


        mimeMessage.setHeader("X-MSMail-Priority", "Normal");

        //以outlook名义发送邮件，不会被当作垃圾邮件
        mimeMessage.setHeader("X-Mailer", "Microsoft Outlook Express 6.00.2900.5512");

        mimeMessage.setHeader("X-MimeOLE", "Produced By Microsoft MimeOLE V6.00.2900.5512");

        mimeMessage.setHeader("ReturnReceipt", "1");
        javaMailSender.send(mimeMessage);
    }


    /**
     *  构建邮件对象
     * @return 邮件对象
     */
    private MimeMessage createMimeMessage() {
        return javaMailSender.createMimeMessage();
    }

    /**
     * 初始化MimeMessageHelper
     * @param mimeMessage  邮件对象
     * @param subject 主题
     * @param from 发件人
     * @param to 收件人
     * @param cc 抄送人，可以有多个抄送人
     * @param bcc 隐秘抄送人，可以有多个
     * @return
     * @throws MessagingException
     */
    @SuppressWarnings("all")
    private MimeMessageHelper initMimeMessageHelper(MimeMessage mimeMessage,String subject,String from,String to,String[] cc,String[] bcc) throws MessagingException {
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        //3. 针对工具类，配置邮件发送的基本信息
        helper.setSubject(subject);
        helper.setFrom(from);
        helper.setTo(to);
        if (cc != null) {
            helper.setCc(cc);
        }
        if (bcc != null) {
            helper.setBcc(bcc);
        }
        helper.setSentDate(new Date());
        return helper;
    }
}
