package com.parker.bbs.async.handler;

import com.parker.bbs.async.event.EventModel;
import com.parker.bbs.async.event.EventType;
import com.parker.bbs.service.MailService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MailEventHandler implements EventHandler,InitializingBean {
    private List<EventType> eventTypes;
    @Autowired
    private MailService mailService;

    @Override
    public void doHandle(EventModel event) {
        String receiver = event.getExtraMap().get("receiver");
        String subject = event.getExtraMap().get("subject");
        String content = event.getExtraMap().get("content");
        mailService.sendMail(receiver, subject, content);
    }

    @Override
    public List<EventType> getSupportEventTypes() {
        return eventTypes;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        eventTypes = new ArrayList<>();
        eventTypes.add(EventType.mail);
    }
}
