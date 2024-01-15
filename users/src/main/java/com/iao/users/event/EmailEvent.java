package com.iao.users.event;

public class  EmailEvent {
    private EventType userEventType;
    private EventData data;

    public EventType getUserEventType() {
        return userEventType;
    }

    public void setUserEventType(EventType userEventType) {
        this.userEventType = userEventType;
    }

    public EventData getData() {
        return data;
    }

    public void setData(EventData userDto) {
        this.data = userDto;
    }

}
