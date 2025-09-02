package com.iokays.sample.core.workflow;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 链式工作流, 将用户输入和系统提示串联起来, 严格依次执行
 * 场景: 文档生成, 数据清洗流水线. 适合顺序依赖的场景.
 */
@Slf4j
@Component
@AllArgsConstructor
public class ChainWorkflow {

    private final ChatClient chatClient;

    public String chain(String userInput, List<String> systemPrompts) {
        String response = userInput;
        for (var prompt : systemPrompts) {
            String input = String.format("{%s}\n{%s}".formatted(prompt, response));
            response = chatClient.prompt().user(input).call().content();
            log.info("input: {}, response: {}", input, response);
        }
        return response;
    }

}
