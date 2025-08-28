package com.iokays.sample.core;

import com.iokays.sample.core.service.RedisPubApplicationService;
import com.iokays.sample.core.service.RedisSubApplicationService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;

@Slf4j
@SpringBootTest
class RedisPubSubApplicationServiceTest {

    @Resource
    private RedisPubApplicationService redisPubApplicationService;

    @Resource
    private RedisSubApplicationService redisSubApplicationService;

    @Test
    void test() {
        redisPubApplicationService.publish("hello");
        redisPubApplicationService.publish("hi");

        await().atMost(3, SECONDS)
                .pollInterval(500, MILLISECONDS)  // 每 500ms 检查一次
                .untilAsserted(() -> {
                    log.info("start check");
                    Assertions.assertTrue(redisSubApplicationService.contains("\"hello\""));
                    Assertions.assertTrue(redisSubApplicationService.contains("\"hi\""));
                });
    }


}