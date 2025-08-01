package com.iokays.sample.core;

import com.iokays.sample.core.service.QueryApplicationService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@SpringBootTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
class QueryApplicationServiceTest {

    @Resource
    private QueryApplicationService queryApplicationService;

    @Test
    void test() {
    }

}


