package com.parker.bbs.async.event;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class EventModel implements Serializable {
    private EventType type;
    private int fromUserId;
    private int toUserId;
    Map<String,String> extra = new HashMap<>();

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public int getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(int fromUserId) {
        this.fromUserId = fromUserId;
    }

    public int getToUserId() {
        return toUserId;
    }

    public void setToUserId(int toUserId) {
        this.toUserId = toUserId;
    }

    public Map<String,String> getExtraMap(){
        return extra;
    }

    public void addExtraAttribute(String key, String value){
        extra.put(key, value);
    }
}
