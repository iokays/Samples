package com.iokays.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class MyAnnotationAsyncApplicationListener {

    private static final Logger logger = LoggerFactory.getLogger(MyAnnotationAsyncApplicationListener.class);

    @Async
    @EventListener
    public void onApplicationEvent(MyApplicationEvent1 event) {
        logger.info("async.event: {}", event);
    }

}
