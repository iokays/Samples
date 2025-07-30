package com.iokays.sample.core.service;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.flowable.task.api.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Map;

@Slf4j
@SpringBootTest
class FlowableApplicationServiceTest {

    @Resource
    private FlowableApplicationService applicationService;

    @Test
    void test() {
        //启动实例
        final String processInstanceId = applicationService.start("LoanRequestProcess", Map.of("customerName", "张三", "amount", BigDecimal.TEN));
        log.debug("processInstanceId: {}", processInstanceId);
        Assertions.assertNotNull(processInstanceId, "实例ID不能为空");

        //查看任务
        final Task task = applicationService.task(processInstanceId);
        Assertions.assertNotNull(task, "任务不能为空");
        Assertions.assertEquals("approveTask", task.getTaskDefinitionKey(), "审批申请任务");

        //认领认领
        applicationService.claim(task.getId(), "李四");

        //完成任务: 异常
        Assertions.assertThrows(Exception.class, () -> {
            applicationService.complete(task.getId(), "张三", Map.of("approvedByManager", true));
        });

        //完成任务: 正常
        applicationService.complete(task.getId(), "李四", Map.of("approvedByManager", true));
    }

}