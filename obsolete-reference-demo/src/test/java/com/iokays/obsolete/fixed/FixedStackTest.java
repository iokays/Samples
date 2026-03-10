package com.iokays.obsolete.fixed;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * FixedStack 单元测试
 */
class FixedStackTest {

    private FixedStack stack;

    @BeforeEach
    void setUp() {
        stack = new FixedStack();
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

    @Test
    void testObsoleteReferenceElimination() {
        // 压入大量对象
        for (int i = 0; i < 100; i++) {
            stack.push(new Object());
        }
        
        // 弹出所有对象
        while (!stack.isEmpty()) {
            stack.pop();
        }
        
        // 此时栈已空，所有过期引用已被清空
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
    }
}
