package com.iokays.work.domain;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Arrays;

@SpringBootTest(classes = Application.class)
public class ApplicationServiceTest {

    @Resource
    ElasticsearchOperations operations;
    @Resource
    ConferenceRepository repository;

    @Resource
    private ApplicationService applicationService;

    @Test
    public void transfer() throws IOException {
        applicationService.transfer();
    }

    @Test
    public void insertDataSample() {
        operations.indexOps(Conference.class).refresh();
        // Save data sample
        var c = new Conference();
        c.setDate("2014-11-06");
        var documents = Arrays.asList(c);
        repository.saveAll(documents);
        operations.indexOps(Conference.class).refresh();
    }

    @Test
    public void count() {
        applicationService.count();
    }

}