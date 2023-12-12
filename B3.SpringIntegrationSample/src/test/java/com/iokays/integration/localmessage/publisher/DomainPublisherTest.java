package com.iokays.integration.localmessage.publisher;

import io.vavr.control.Try;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class DomainPublisherTest {

    @Resource
    private MyApplicationServiceTestImpl service;

    @Test
    public void testPublish() {
        service.create();
        Try.run(() -> Thread.sleep(1000));
    }

}