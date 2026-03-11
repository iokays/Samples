package com.iokays.cleaner.bad;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * CleanerMisuseDemo 测试
 */
class CleanerMisuseDemoTest {
    
    @BeforeEach
    void setUp() {
        CleanerMisuseDemo.reset();
    }
    
    @Test
    void testCreation() {
        CleanerMisuseDemo demo = new CleanerMisuseDemo();
        assertEquals(1, demo.getId());
        assertEquals(1, CleanerMisuseDemo.getInstanceCount());
    }
    
    @Test
    void testExplicitClose() {
        try (CleanerMisuseDemo demo = new CleanerMisuseDemo()) {
            assertNotNull(demo);
        }
        assertEquals(1, CleanerMisuseDemo.getCleanedCount());
    }
    
    @Test
    void testMultipleExplicitClose() {
        try (CleanerMisuseDemo d1 = new CleanerMisuseDemo();
             CleanerMisuseDemo d2 = new CleanerMisuseDemo()) {
            // 使用资源
        }
        assertEquals(2, CleanerMisuseDemo.getCleanedCount());
    }
    
    @Test
    void testCleanerUnpredictability() {
        // 这个测试展示 Cleaner 的不可预测性
        // 虽然 Cleaner 比 Finalizer 稍好，但仍不保证及时执行
        
        // 创建对象但不关闭
        new CleanerMisuseDemo();
        new CleanerMisuseDemo();
        
        // 建议 GC（但不保证执行）
        System.gc();
        
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // 核心问题：Cleaner 的执行时间和次数都不可预测
        System.out.println("Cleaner 已清理: " + CleanerMisuseDemo.getCleanedCount());
        // 不做断言，仅展示不可预测性
    }
    
    @Test
    void testReset() {
        new CleanerMisuseDemo();
        new CleanerMisuseDemo();
        assertEquals(2, CleanerMisuseDemo.getInstanceCount());
        
        CleanerMisuseDemo.reset();
        assertEquals(0, CleanerMisuseDemo.getInstanceCount());
        assertEquals(0, CleanerMisuseDemo.getCleanedCount());
    }
}
