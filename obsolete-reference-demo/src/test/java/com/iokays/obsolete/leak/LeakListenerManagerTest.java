package com.iokays.obsolete.leak;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * LeakListenerManager 单元测试
 */
class LeakListenerManagerTest {

    private LeakListenerManager<String> manager;

    @BeforeEach
    void setUp() {
        manager = new LeakListenerManager<>();
    }

    @Test
    void testAddListener() {
        manager.addListener("listener1");
        manager.addListener("listener2");
        
        assertEquals(2, manager.listenerCount());
    }

    @Test
    void testRemoveListener() {
        manager.addListener("listener1");
        manager.addListener("listener2");
        
        manager.removeListener("listener1");
        
        assertEquals(1, manager.listenerCount());
    }

    @Test
    void testNotifyAll() {
        StringBuilder result = new StringBuilder();
        
        manager.addListener("A");
        manager.addListener("B");
        manager.addListener("C");
        
        manager.notifyAll(result::append);
        
        assertEquals("ABC", result.toString());
    }

    @Test
    void testClear() {
        manager.addListener("listener1");
        manager.addListener("listener2");
        
        manager.clear();
        
        assertEquals(0, manager.listenerCount());
    }

    @Test
    void testDuplicateListener() {
        manager.addListener("listener");
        manager.addListener("listener");
        
        assertEquals(2, manager.listenerCount());
    }
}
