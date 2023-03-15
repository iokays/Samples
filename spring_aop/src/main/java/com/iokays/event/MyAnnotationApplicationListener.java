package com.iokays.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class MyAnnotationApplicationListener {

    private static final Logger logger = LoggerFactory.getLogger(MyAnnotationApplicationListener.class);

    @EventListener
    public void onApplicationEvent(MyApplicationEvent1 event) {
        logger.info("event: {}", event);
    }

}
