package com.iokays.cleaner.bad;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * FinalizerDemo 测试
 * 
 * 这些测试展示了 finalize() 的各种问题
 */
class FinalizerDemoTest {
    
    @BeforeEach
    void setUp() {
        FinalizerDemo.reset();
    }
    
    @Test
    void testCreation() {
        FinalizerDemo demo = new FinalizerDemo();
        assertEquals(1, demo.getId());
        assertEquals(1, FinalizerDemo.getInstanceCount());
    }
    
    @Test
    void testMultipleInstances() {
        for (int i = 0; i < 5; i++) {
            new FinalizerDemo();
        }
        assertEquals(5, FinalizerDemo.getInstanceCount());
    }
    
    @Test
    void testFinalizerUnpredictability() {
        // 这个测试展示 finalize() 的不可预测性
        // 它可能执行，也可能不执行，也可能执行多次
        
        // 创建对象但不保持引用
        new FinalizerDemo();
        new FinalizerDemo();
        new FinalizerDemo();
        
        // 建议 GC（但不保证执行）
        System.gc();
        
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // 核心问题：finalize() 的执行时间和次数都不可预测
        // 这个测试通过，恰恰证明了 finalize() 的不可靠性
        System.out.println("finalize() 已终结: " + FinalizerDemo.getFinalizedCount());
        // 不做断言，仅展示不可预测性
    }
    
    @Test
    void testGetInstanceCount() {
        assertEquals(0, FinalizerDemo.getInstanceCount());
        new FinalizerDemo();
        assertEquals(1, FinalizerDemo.getInstanceCount());
    }
    
    @Test
    void testReset() {
        new FinalizerDemo();
        new FinalizerDemo();
        assertEquals(2, FinalizerDemo.getInstanceCount());
        
        FinalizerDemo.reset();
        assertEquals(0, FinalizerDemo.getInstanceCount());
        assertEquals(0, FinalizerDemo.getFinalizedCount());
    }
}
