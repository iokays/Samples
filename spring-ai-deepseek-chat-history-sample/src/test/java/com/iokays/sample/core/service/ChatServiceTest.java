package com.iokays.sample.core.service;

import com.iokays.sample.core.service.model.ConversationModel;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ChatServiceTest {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(ChatServiceTest.class);
    @Resource
    ChatMemory chatMemory;
    @Resource
    private ChatService chatService;

    @Test
    public void test() {
        final ConversationModel first = ConversationModel.builder().message("你好, 请你扮演一个角色, 角色的名字是露娜").build();
        final ConversationModel conversation1 = chatService.conversation(first);
        log.info("conversation1: {}", conversation1);

        final ConversationModel second = ConversationModel.builder().conversationId(conversation1.conversationId()).message("请回答我, 你是谁呢?").build();
        final ConversationModel conversation2 = chatService.conversation(second);
        log.info("conversation2: {}", conversation2);
        Assertions.assertTrue(StringUtils.containsAny(conversation2.message(), "露娜"));

        Assertions.assertEquals(4, chatMemory.get(conversation1.conversationId()).size());

    }

}


