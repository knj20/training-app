package com.iao.email.emails.event;

public class  EmailEvent {
    private EventType userEventType;
    private EventData data;

    public EmailEvent()
    {

    }

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
