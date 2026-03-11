package com.iokays.cleaner.good;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Resource 测试
 */
class ResourceTest {
    
    @BeforeEach
    void setUp() {
        Resource.reset();
    }
    
    @Test
    void testCreation() {
        Resource resource = new Resource();
        assertEquals(1, resource.getId());
        assertEquals(1, Resource.getInstanceCount());
        assertFalse(resource.isClosed());
    }
    
    @Test
    void testExplicitClose() {
        Resource resource = new Resource();
        assertFalse(resource.isClosed());
        
        resource.close();
        assertTrue(resource.isClosed());
        assertEquals(1, Resource.getClosedCount());
    }
    
    @Test
    void testCloseIsIdempotent() {
        Resource resource = new Resource();
        
        // 多次关闭应该安全
        resource.close();
        resource.close();
        resource.close();
        
        assertTrue(resource.isClosed());
        assertEquals(1, Resource.getClosedCount());
    }
    
    @Test
    void testTryWithResources() {
        try (Resource r1 = new Resource();
             Resource r2 = new Resource()) {
            assertFalse(r1.isClosed());
            assertFalse(r2.isClosed());
        }
        
        assertEquals(2, Resource.getClosedCount());
    }
    
    @Test
    void testUseAfterClose() {
        Resource resource = new Resource();
        resource.close();
        
        assertThrows(IllegalStateException.class, () -> {
            resource.doSomething();
        });
    }
    
    @Test
    void testDoSomething() {
        Resource resource = new Resource();
        assertDoesNotThrow(() -> resource.doSomething());
        assertFalse(resource.isClosed());
    }
    
    @Test
    void testMultipleResources() {
        try (Resource r1 = new Resource();
             Resource r2 = new Resource();
             Resource r3 = new Resource()) {
            r1.doSomething();
            r2.doSomething();
            r3.doSomething();
        }
        
        assertEquals(3, Resource.getInstanceCount());
        assertEquals(3, Resource.getClosedCount());
    }
    
    @Test
    void testReset() {
        new Resource();
        new Resource();
        assertEquals(2, Resource.getInstanceCount());
        
        Resource.reset();
        assertEquals(0, Resource.getInstanceCount());
        assertEquals(0, Resource.getClosedCount());
    }
}
