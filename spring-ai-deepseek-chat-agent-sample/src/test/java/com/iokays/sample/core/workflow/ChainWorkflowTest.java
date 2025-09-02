package com.iokays.sample.core.workflow;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
class ChainWorkflowTest {

    @Resource
    private ChainWorkflow chainWorkflow;

    @Test
    public void test() {
        final var userInput = "创建一个线程安全的用户计数器";
        final var systemPrompts = List.of(
                "请用一句话总结以下技术需求的核心目标",
                "根据以下目标,列出5个关键章节标题:",
                "为每一个章节编写详细说明:"
        );

        final String response = chainWorkflow.chain(userInput, systemPrompts);
        log.info("userInput: {}, response: {}", userInput, response);


    }


}