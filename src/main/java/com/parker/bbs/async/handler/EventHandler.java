package com.parker.bbs.async.handler;

import com.parker.bbs.async.event.EventModel;
import com.parker.bbs.async.event.EventType;

import java.util.List;

public interface EventHandler {
    void doHandle(EventModel event);
    List<EventType> getSupportEventTypes();
}
