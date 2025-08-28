package com.iokays.sample.core;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;

@SpringBootTest
public class ElasticsearchOperationsTest {

    @Resource
    private ElasticsearchOperations elasticsearchOperations;

    @Test
    public void testDelete() {
        final var indexName = "person";
        elasticsearchOperations.indexOps(IndexCoordinates.of(indexName)).delete();
    }

}
