package com.iokays.sample.core.workflow;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class EvaluatorOptimizerWorkflow {

    private final ChatClient chatClient;

    public String loop(String task) {
        var code = chatClient.prompt()
                .system("生成Java代码实现以下需求：")
                .user(task)
                .call()
                .content();

        for (int i = 0; i < 3; i++) {
            String feedback = chatClient.prompt()
                    .system("从线程安全、性能、可读性三方面评价代码：")
                    .user(code)
                    .call()
                    .content();

            code = chatClient.prompt()
                    .system("根据以下反馈优化代码：")
                    .user(feedback + "\n原始代码：" + code)
                    .call()
                    .content();
        }

        return code;
    }

}
