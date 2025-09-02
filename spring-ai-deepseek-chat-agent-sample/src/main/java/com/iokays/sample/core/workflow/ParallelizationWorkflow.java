package com.iokays.sample.core.workflow;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 并行工作流, 将用户输入和系统提示并行执行
 * 场景: 同时分析多个数据源, 适合无顺序依赖的场景.
 */
@Slf4j
@Component
@AllArgsConstructor
public class ParallelizationWorkflow {

    private final ChatClient chatClient;

    public List<String> parallel(String systemPrompt, List<String> userInputs) {
        return userInputs
                .parallelStream()
                .map(v -> chatClient.prompt(systemPrompt).user(v).call().content())
                .toList();
    }
}
