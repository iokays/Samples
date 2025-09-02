package com.iokays.sample.core.workflow;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Validate;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 路由工作流: 通过一个分类步骤来决定后续的处理路径。
 * 场景:
 */
@Slf4j
@Component
@AllArgsConstructor
public class RoutingWorkflow {

    private final ChatClient chatClient;

    public String route(String userInput, Map<String, String> routes) {

        final var systemPrompt = "对用户的提问进行分类, 请直接回答如下分类(%s)：".formatted(String.join("/", routes.keySet()));

        String category = chatClient.prompt()
                .system(systemPrompt)
                .user(userInput)
                .call()
                .content();

        log.info("systemPrompt: {}, category: {}", systemPrompt, category);

        Validate.isTrue(routes.containsKey(category), "识别的分类: %s", category);

        return chatClient.prompt().system(routes.get(category)).user(userInput).call().content();
    }

}
