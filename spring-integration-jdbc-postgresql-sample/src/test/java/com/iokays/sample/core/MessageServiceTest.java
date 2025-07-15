
package com.iokays.sample.core;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.Message;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@Slf4j
@SpringBootTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
class MessageServiceTest {

    @Resource
    private MessageService messageService;

    @Test
    void test() {
        messageService.send("This is a message.");
        messageService.send("This is a new message.");

        // 等待并重试检查
        await().atMost(2, TimeUnit.SECONDS)
                .untilAsserted(() -> {
                    final List<Message<?>> messages = messageService.receivedMessages();
                    assertFalse(CollectionUtils.isEmpty(messages), "No message received");
                    assertEquals(2, messages.size());
                    assertEquals("This is a message.", messages.getFirst().getPayload());
                    assertEquals("This is a new message.", messages.getLast().getPayload());
                });

        log.info("result: {}", messageService.receivedMessages());
    }

}


