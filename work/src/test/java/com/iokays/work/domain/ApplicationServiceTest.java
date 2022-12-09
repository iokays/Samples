package com.iokays.work.domain;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

import java.io.IOException;

@SpringBootTest(classes = Application.class)
public class ApplicationServiceTest {

    @Resource
    ElasticsearchOperations operations;

    @Resource
    private ApplicationService applicationService;

    @Test
    public void transfer() throws IOException {
        applicationService.transfer();
    }

    @Test
    public void count() {
        applicationService.count();
    }

}