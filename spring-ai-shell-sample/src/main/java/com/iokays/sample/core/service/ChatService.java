package com.iokays.sample.core.service;

import com.iokays.sample.core.service.model.ConversationModel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class ChatService {

    private final ChatClient chatClient;

    public ConversationModel conversation(ConversationModel model) {
        final var conversationId = Optional.ofNullable(model.conversationId())
                .filter(StringUtils::isNotBlank)
                .orElseGet(() -> UUID.randomUUID().toString());

        final String result = chatClient.prompt()
                .advisors(a -> a.param(ChatMemory.CONVERSATION_ID, conversationId))
                .user(model.message())
                .call()
                .content();

        return ConversationModel.builder().conversationId(conversationId).message(result).build();
    }

}

