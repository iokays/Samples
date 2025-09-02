package com.iokays.sample.core.workflow;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class EvaluatorOptimizerWorkflowTest {

    @Resource
    EvaluatorOptimizerWorkflow evaluatorOptimizerWorkflow;

    @Test
    public void test() {
        final String loop = evaluatorOptimizerWorkflow.loop("线程安全的计数器");
        log.info("loop: {}", loop);

    }

}