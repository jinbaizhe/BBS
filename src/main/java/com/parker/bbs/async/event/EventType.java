package com.parker.bbs.async.event;

/**
 * @author viruser
 */

public enum EventType {
    mail(0),others(1);
    private int value;
    EventType(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
