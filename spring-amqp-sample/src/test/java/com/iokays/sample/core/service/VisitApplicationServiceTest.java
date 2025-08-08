package com.iokays.sample.core.service;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;


@Slf4j
@SpringBootTest
class VisitApplicationServiceTest {

    @Resource
    private VisitApplicationService visitApplicationService;

    @Resource
    private CounterApplicationService counterApplicationService;

    @Test
    void test() {
        final String customerId = "1234567890";
        visitApplicationService.visit(customerId);
        visitApplicationService.visit(customerId);
        final LocalDateTime time = LocalDateTime.now();
        visitApplicationService.visit(customerId);
        final LocalDateTime time2 = LocalDateTime.now();

        await().atMost(3, SECONDS)
                .pollInterval(500, MILLISECONDS)  // 每 500ms 检查一次
                .untilAsserted(() -> {
                    final Pair<LocalDateTime, Long> pair = counterApplicationService.get(customerId);
                    Assertions.assertNotNull(pair);
                    // direct: 3, fanout; 6, topic: 6, headers: 6 --> 21
                    Assertions.assertEquals(21L, pair.getValue());
                    Assertions.assertTrue(pair.getKey().isBefore(time2) && pair.getKey().isAfter(time));
                    log.info("pair: {}, count: {}", pair, pair.getValue());
                });
    }

}