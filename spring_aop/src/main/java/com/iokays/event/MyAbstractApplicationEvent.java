package com.iokays.event;

import org.springframework.context.ApplicationEvent;

public abstract class MyAbstractApplicationEvent extends ApplicationEvent {

    public MyAbstractApplicationEvent(Object source) {
        super(source);
    }
}
