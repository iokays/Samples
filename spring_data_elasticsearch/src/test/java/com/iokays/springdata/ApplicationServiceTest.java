package com.iokays.springdata;


import example.springdata.elasticsearch.conference.ApplicationConfiguration;
import example.springdata.elasticsearch.conference.Conference;
import example.springdata.elasticsearch.conference.ConferenceRepository;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@SpringBootTest(classes = ApplicationConfiguration.class)
public class ApplicationServiceTest {

    @Autowired ElasticsearchOperations operations;
    @Autowired ConferenceRepository repository;

    @Test
    public void deleteIndex() {
        operations.indexOps(Conference.class).delete();
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

}