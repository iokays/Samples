package com.iokays.boot.integration;

import org.springframework.integration.core.GenericHandler;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

/**
 * Spring Integration消息发送
 *
 */
@Component
public class UserRegisterSendHandler implements GenericHandler<User> {

    @Override
    public Object handle(User user, MessageHeaders messageHeaders) {
        System.out.println(user);
        return null;
    }
}

