package com.iokays.sample.core.workflow;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class OrchestratorWorkersWorkflow {

    private final ChatClient chatClient;

    public String process(String product) {
        // 1. Orchestrator analyzes task and determines subtasks: 协调器分解任务
        final var subTasks = chatClient.prompt()
                .system("将以下产品分析任务分解为3个子任务：")
                .user(product)
                .call()
                .content();

        log.info("subTasks: {}", subTasks);

        // 2. Workers process subtasks in parallel: 工作者并行执行
        final List<String> workerResults = Arrays.stream(subTasks.split(StringUtils.LF + StringUtils.LF))
                .parallel()
                .map(task -> chatClient.prompt().system("执行专业市场分析：").user(task).call().content())
                .toList();

        // 3. Results are combined into final response: 协调器汇总
        return chatClient.prompt().system("整合以下分析报告：").user(String.join(StringUtils.LF, workerResults)).call().content();
    }

}
