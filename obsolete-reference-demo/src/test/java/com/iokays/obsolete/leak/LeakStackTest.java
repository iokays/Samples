package com.iokays.obsolete.leak;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * LeakStack 单元测试
 */
class LeakStackTest {
    
    private LeakStack stack;

    @BeforeEach
    void setUp() {
        stack = new LeakStack();
    }

    @Test
    void testPushAndPop() {
        Object obj = new Object();
        stack.push(obj);
        
        assertEquals(1, stack.size());
        assertFalse(stack.isEmpty());
        
        Object popped = stack.pop();
        assertSame(obj, popped);
        assertEquals(0, stack.size());
        assertTrue(stack.isEmpty());
    }

    @Test
    void testPopEmptyStack() {
        assertThrows(EmptyStackException.class, () -> stack.pop());
    }

    @Test
    void testMultiplePushPop() {
        for (int i = 0; i < 100; i++) {
            stack.push(i);
        }
        assertEquals(100, stack.size());

        // 弹出所有元素
        for (int i = 99; i >= 0; i--) {
            assertEquals(i, stack.pop());
        }
        assertTrue(stack.isEmpty());
    }

    @Test
    void testCapacityExpansion() {
        // 压入超过默认容量的元素
        for (int i = 0; i < 100; i++) {
            stack.push(i);
        }
        assertEquals(100, stack.size());
        
        // 验证所有元素都能正确弹出
        for (int i = 99; i >= 0; i--) {
            assertEquals(i, stack.pop());
        }
    }

    @Test
    void testIsEmpty() {
        assertTrue(stack.isEmpty());
        stack.push(new Object());
        assertFalse(stack.isEmpty());
    }
}
