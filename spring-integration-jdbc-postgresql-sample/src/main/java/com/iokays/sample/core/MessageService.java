
package com.iokays.sample.core;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.jdbc.channel.PostgresSubscribableChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class MessageService {

    private final PostgresSubscribableChannel subscribableChannel;
    private final QueueChannel queueChannel;

    private final List<Message<?>> receivedMessage = Collections.synchronizedList(new ArrayList<>());

    /**
     * 订阅
     */
    @PostConstruct
    private void subscribe() {
        subscribableChannel.subscribe(message -> {
            log.debug("message: {}", message);
            // 还可以发送给MQ: 实现分布式事务-本地消息表 (经典的ebay模式)
            receivedMessage.add(message);
        });
    }

    public void send(String message) {
        queueChannel.send(MessageBuilder.withPayload(message).build());
    }

    protected List<Message<?>> receivedMessages() {
        return Collections.unmodifiableList(receivedMessage);
    }

}

