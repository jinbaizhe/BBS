package com.parker.bbs.service;

import org.springframework.beans.factory.InitializingBean;

public interface MailService extends InitializingBean {
    void sendMail(String receiver, String subject, String content);
}
