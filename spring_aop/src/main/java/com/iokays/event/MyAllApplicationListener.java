package com.iokays.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 用于全局的事件处理器
 */
@Component
public class MyAllApplicationListener implements ApplicationListener<MyAbstractApplicationEvent> {
    private static final Logger logger = LoggerFactory.getLogger(MyAllApplicationListener.class);
    @Override
    public void onApplicationEvent(MyAbstractApplicationEvent event) {
        logger.info("event: {}", event);
    }
}
