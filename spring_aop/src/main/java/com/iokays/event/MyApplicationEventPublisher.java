package com.iokays.event;

import jakarta.annotation.Resource;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class MyApplicationEventPublisher {

    @Resource
    private ApplicationEventPublisher applicationEventPublisher;

    public void publicEvent() {
        applicationEventPublisher.publishEvent(new MyApplicationEvent1("event1"));
        applicationEventPublisher.publishEvent(new MyApplicationEvent2("event2"));
    }

    public static void main(String[] args) {
    }

}
