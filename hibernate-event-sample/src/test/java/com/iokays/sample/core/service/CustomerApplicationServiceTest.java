package com.iokays.sample.core.service;

import com.iokays.sample.core.domain.event.CustomerCreated;
import com.iokays.sample.core.domain.event.CustomerDeleted;
import com.iokays.sample.core.domain.event.CustomerUpdated;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@SpringBootTest
@Transactional(propagation = Propagation.NEVER)
class CustomerApplicationServiceTest {

    @Resource
    private CustomerApplicationService service;

    @Test
    void test() {
        final String id = service.create("Andy");
        service.update(id, "Tim");
        service.delete(id);

        final List<?> events = TestDomainEventListener.events.stream().toList();

        log.info("events: {}", events);

        Assertions.assertEquals(3, events.size());
        Assertions.assertEquals(CustomerCreated.class, events.get(0).getClass());
        Assertions.assertEquals(CustomerUpdated.class, events.get(1).getClass());
        Assertions.assertEquals(CustomerDeleted.class, events.get(2).getClass());

    }

}