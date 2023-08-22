package com.iokays.boot.integration.distributedlock;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JDBCLockServiceTest {

    @Resource
    private LockService lockService;

    @Test
    public void testLock() {
        Assertions.assertEquals("jdbc lock successful", lockService.lock());
        Assertions.assertEquals("jdbc lock successful", lockService.properLock());
        lockService.failLock();
    }

}