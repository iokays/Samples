package com.iokays.sample.core.workflow;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
class ParallelizationWorkflowTest {

    @Resource
    private ParallelizationWorkflow parallelizationWorkflow;

    @Test
    void test() {
        final var systemPrompt = "分析情感(正面/负面), 提取关键字,给出改进建议";
        final var userInputs = List.of(
                "电池续航差但屏幕好",
                "系统流畅度不足"
        );

        final List<String> parallel = parallelizationWorkflow.parallel(systemPrompt, userInputs);
        log.info("systemPrompt: {}, userInputs: {}, parallel: {}", systemPrompt, userInputs, parallel);
    }

}