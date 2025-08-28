package com.iokays.sample.core;

import com.iokays.sample.core.service.RedisStreamApplicationService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;

@Slf4j
@SpringBootTest
class RedisSteamApplicationServiceTest {

    @Resource
    private RedisStreamApplicationService redisStreamApplicationService;

    @Test
    void test() {
        final Map<String, String> map = Map.of("hello", "world");
        redisStreamApplicationService.publish(map);

        await().atMost(3, SECONDS)
                .pollInterval(500, MILLISECONDS)  // 每 500ms 检查一次
                .untilAsserted(() -> {
                    log.info("start check");
                    Assertions.assertTrue(redisStreamApplicationService.hasConsumed());
                });
    }


}