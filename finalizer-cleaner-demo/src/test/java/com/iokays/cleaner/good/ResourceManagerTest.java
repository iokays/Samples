package com.iokays.cleaner.good;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ResourceManager 测试
 */
class ResourceManagerTest {
    
    @BeforeEach
    void setUp() {
        Resource.reset();
    }
    
    @Test
    void testCreation() {
        ResourceManager manager = new ResourceManager();
        assertFalse(manager.isClosed());
        assertEquals(0, manager.getResourceCount());
    }
    
    @Test
    void testCreateResource() {
        ResourceManager manager = new ResourceManager();
        Resource r1 = manager.createResource();
        Resource r2 = manager.createResource();
        
        assertEquals(2, manager.getResourceCount());
        assertEquals(2, Resource.getInstanceCount());
    }
    
    @Test
    void testCloseAllResources() {
        ResourceManager manager = new ResourceManager();
        manager.createResource();
        manager.createResource();
        manager.createResource();
        
        manager.close();
        
        assertTrue(manager.isClosed());
        assertEquals(3, Resource.getClosedCount());
    }
    
    @Test
    void testTryWithResources() {
        try (ResourceManager manager = new ResourceManager()) {
            manager.createResource();
            manager.createResource();
            assertEquals(2, Resource.getInstanceCount());
        }
        
        assertEquals(2, Resource.getClosedCount());
    }
    
    @Test
    void testCreateAfterClose() {
        ResourceManager manager = new ResourceManager();
        manager.close();
        
        assertThrows(IllegalStateException.class, () -> {
            manager.createResource();
        });
    }
    
    @Test
    void testCloseIsIdempotent() {
        ResourceManager manager = new ResourceManager();
        manager.createResource();
        
        manager.close();
        manager.close();
        manager.close();
        
        assertTrue(manager.isClosed());
        assertEquals(1, Resource.getClosedCount());
    }
}
