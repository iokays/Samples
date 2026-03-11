package com.iokays.cleaner.demo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * CleanerDemo 测试
 */
class CleanerDemoTest {
    
    @Test
    void testMain() {
        // 测试主程序能否正常执行
        assertDoesNotThrow(() -> {
            CleanerDemo.main(new String[]{});
        });
    }
}
