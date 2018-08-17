package com.parker.bbs.service.serviceImpl;

import com.parker.bbs.service.MailService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

@Service
public class MailServiceImpl implements MailService {
    private Logger logger = LogManager.getLogger(MailServiceImpl.class);
    private JavaMailSenderImpl mailSender;
    @Value("#{configProperties['mail.host']}")
    private String host;
    @Value("#{configProperties['mail.port']}")
    private int port;
    @Value("#{configProperties['mail.username']}")
    private String username;
    @Value("#{configProperties['mail.password']}")
    private String password;
    @Value("#{configProperties['mail.defaultEncoding']}")
    private String defaultEncoding;
    @Value("#{configProperties['mail.timeout']}")
    private String timeout;
    @Value("#{configProperties['mail.auth']}")
    private String auth;
    @Value("#{configProperties['mail.personal']}")
    private String personal;
    @Override
    public void sendMail(String receiver, String subject, String content) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        // 设置utf-8或GBK编码，否则邮件会有乱码
        MimeMessageHelper messageHelper = null;
        try {
            messageHelper = new MimeMessageHelper(mimeMessage, true, defaultEncoding);
            messageHelper.setFrom(username, personal);
            messageHelper.setTo(receiver);
            messageHelper.setSubject(subject);
            messageHelper.setText(content, true);
            mailSender.send(mimeMessage);
        } catch (MessagingException|UnsupportedEncodingException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setUsername(username);
        mailSender.setPassword(password);
        mailSender.setDefaultEncoding(defaultEncoding);
        Properties p = new Properties();
        p.setProperty("mail.smtp.timeout", timeout);
        p.setProperty("mail.smtp.auth", auth);
        mailSender.setJavaMailProperties(p);
    }
}
