package com.iokays.sample.core.workflow;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class OrchestratorWorkersWorkflowTest {

    @Resource
    private OrchestratorWorkersWorkflow orchestratorWorkersWorkflow;

    @Test
    void test() {
        final String result = orchestratorWorkersWorkflow.process("iPhone 17");
        log.info("result: {}", result);
    }

}