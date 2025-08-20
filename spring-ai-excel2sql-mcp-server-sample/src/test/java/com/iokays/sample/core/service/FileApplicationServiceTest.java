package com.iokays.sample.core.service;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;

@SpringBootTest
class FileApplicationServiceTest {

    @Resource
    private FileApplicationService fileApplicationService;

    @Resource
    private ResourceLoader resourceLoader;

    @Test
    void test() throws IOException {
        final var resource = resourceLoader.getResource("classpath:file.xlsx");
        fileApplicationService.uploadFile("file.xlsx", resource.getContentAsByteArray());
    }

}