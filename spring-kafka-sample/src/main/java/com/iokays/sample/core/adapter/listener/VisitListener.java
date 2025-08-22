package com.iokays.sample.core.adapter.listener;

import com.iokays.sample.core.service.CounterApplicationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
@AllArgsConstructor
public class VisitListener {

    private final CounterApplicationService counterApplicationService;

    @KafkaListener(topics = "visit", groupId = "visit-group-1")
    public void listen(
            @Header(name = KafkaHeaders.RECEIVED_KEY, required = false) String key,
            @Payload LocalDateTime time) {
        log.info("收到消息: key: {}, time: {}", key, time);
        counterApplicationService.increment(key, time);
    }

    @KafkaListener(topics = "visit", groupId = "visit-group-2")
    public void listen2(
            @Header(name = KafkaHeaders.RECEIVED_KEY, required = false) String key,
            @Payload LocalDateTime time) {
        log.info("收到消息: key: {}, time: {}", key, time);
        counterApplicationService.increment(key, time);
    }
}
