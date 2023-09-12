package com.iokays.boot.redis.inegration.distributedlock;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.integration.support.locks.LockRegistry;

@SpringBootTest
public class Tester {

    @Resource
    private LockRegistry lockRegistry;

    @Test
    public void testLock() {
        final String key = "redis-lock";
        final var lock = lockRegistry.obtain(key);

        try {
            lock.lock();
            System.out.println("redis lock successful");
        } finally {
            lock.unlock();
        }
    }



}
