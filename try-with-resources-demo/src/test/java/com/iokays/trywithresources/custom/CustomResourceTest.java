package com.iokays.trywithresources.custom;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 测试自定义资源类
 */
class CustomResourceTest {
    
    @Test
    void testBasicUsage() throws Exception {
        // 测试基本用法：资源会自动关闭
        try (CustomResource resource = new CustomResource("Test")) {
            assertFalse(resource.isClosed());
            resource.doSomething();
        }
        // 退出 try 块后，资源应该被关闭
    }
    
    @Test
    void testResourceClosedAfterTry() throws Exception {
        CustomResource resource;
        
        try (CustomResource r = new CustomResource("Test")) {
            resource = r;
            assertFalse(r.isClosed());
        }
        
        assertTrue(resource.isClosed());
    }
    
    @Test
    void testClosedResourceThrowsException() throws Exception {
        CustomResource resource = new CustomResource("Test");
        resource.close();
        
        assertTrue(resource.isClosed());
        assertThrows(IllegalStateException.class, () -> {
            resource.doSomething();
        });
    }
    
    @Test
    void testExceptionSuppression() {
        // 测试异常抑制机制
        Exception exception = assertThrows(Exception.class, () -> {
            try (CustomResource resource = new CustomResource("Test")) {
                resource.setThrowOnClose(true);
                resource.doSomethingWithException();
            }
        });
        
        // 主要异常应该是业务操作抛出的
        assertEquals("Business logic exception from Test", exception.getMessage());
        
        // close() 抛出的异常应该被抑制
        assertEquals(1, exception.getSuppressed().length);
        assertEquals("Close exception from Test", exception.getSuppressed()[0].getMessage());
    }
    
    @Test
    void testMultipleResources() throws Exception {
        // 测试多个资源的自动管理
        CustomResource r1, r2, r3;
        
        try (CustomResource resource1 = new CustomResource("R1");
             CustomResource resource2 = new CustomResource("R2");
             CustomResource resource3 = new CustomResource("R3")) {
            
            r1 = resource1;
            r2 = resource2;
            r3 = resource3;
            
            assertFalse(r1.isClosed());
            assertFalse(r2.isClosed());
            assertFalse(r3.isClosed());
        }
        
        // 所有资源都应该被关闭（反向顺序）
        assertTrue(r1.isClosed());
        assertTrue(r2.isClosed());
        assertTrue(r3.isClosed());
    }
    
    @Test
    void testCloseIdempotent() throws Exception {
        // 测试 close 方法是幂等的
        CustomResource resource = new CustomResource("Test");
        
        resource.close();
        assertTrue(resource.isClosed());
        
        // 多次调用 close 不应该抛出异常
        assertDoesNotThrow(() -> resource.close());
        assertTrue(resource.isClosed());
    }
    
    @Test
    void testCloseExceptionOnly() throws Exception {
        // 测试只有 close 抛出异常的情况
        Exception exception = assertThrows(Exception.class, () -> {
            try (CustomResource resource = new CustomResource("Test")) {
                resource.setThrowOnClose(true);
                // 正常操作，没有异常
                resource.doSomething();
            }
        });
        
        // 异常应该是 close 抛出的
        assertEquals("Close exception from Test", exception.getMessage());
        assertEquals(0, exception.getSuppressed().length);
    }
}
