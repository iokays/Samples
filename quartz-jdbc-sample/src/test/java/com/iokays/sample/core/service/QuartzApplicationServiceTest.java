package com.iokays.sample.core.service;

import com.iokays.sample.core.adapter.job.SampleJob;
import com.iokays.sample.core.service.command.CreateJob;
import io.vavr.control.Try;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@SpringBootTest
@Transactional(propagation = Propagation.NEVER)
class QuartzApplicationServiceTest {

    private final String name = "sample-job";
    private final String group = "sample-job-group";
    @Resource
    private QuartzApplicationService applicationService;

    @BeforeEach
    public void setUp() {
        applicationService.deleteJob(name, group);
    }


    @Test
    public void test() {
        final var job = CreateJob.builder()
                .name(name)
                .group(group)
                .jobClass(SampleJob.class)
                .cronExpression("0/1 * * * * ?")
                .build();
        applicationService.scheduleJob(job);

        Assertions.assertThrows(RuntimeException.class, () -> {
            applicationService.scheduleJob(job);
        });

        // 等待任务执行, 查看打印日志
        Try.run(() -> Thread.sleep(300L));
    }
}