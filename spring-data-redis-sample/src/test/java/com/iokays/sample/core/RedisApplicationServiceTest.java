package com.iokays.sample.core;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Set;

@Slf4j
@SpringBootTest
class RedisApplicationServiceTest {

    @Resource
    private RedisApplicationService redisApplicationService;

    @BeforeEach
    public void init() {
        redisApplicationService.delete("key");
    }

    @Test
    public void testStr() {
        redisApplicationService.set("key", "value");
        Assertions.assertEquals("value", redisApplicationService.get("key"));
    }

    @Test
    public void testHash() {
        redisApplicationService.setHash("key", "hashKey", "value");
        Assertions.assertEquals("value", redisApplicationService.getHash("key", "hashKey"));
    }

    @Test
    public void testList() {
        redisApplicationService.addList("key", "value");
        redisApplicationService.addList("key", "value");
        final List<Object> values = redisApplicationService.getList("key");
        Assertions.assertEquals(2, values.size());
    }

    @Test
    public void testSet() {
        redisApplicationService.addSet("key", "value");
        redisApplicationService.addSet("key", "value");
        final Set<Object> values = redisApplicationService.getSet("key");
        Assertions.assertEquals(1, values.size());
    }

    @Test
    public void testZSet() {
        redisApplicationService.addZSet("key", "value3", 1.7);
        redisApplicationService.addZSet("key", "value", 1.0);
        redisApplicationService.addZSet("key", "value2", 1.5);
        final Set<Object> values = redisApplicationService.getZSet("key");
        log.info("values: {}", values);
        Assertions.assertEquals(3, values.size());
        Assertions.assertEquals("value", values.stream().findFirst().orElseThrow());
    }

    @Test
    public void testBit() {
        redisApplicationService.setBit("key", 100, true);
        Assertions.assertTrue(redisApplicationService.getBit("key", 100));
        Assertions.assertFalse(redisApplicationService.getBit("key", 99));
    }

}