package com.iokays.obsolete.fixed;

import java.util.EmptyStackException;

/**
 * 修复内存泄漏的栈实现
 * 
 * <p>通过在弹出元素时清空过期引用来避免内存泄漏。
 * 
 * <p>一旦对象引用过期，将它们设置为 null。
 * 这样垃圾收集器就能正确回收不再使用的对象。
 */
public class FixedStack {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public FixedStack() {
        elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(Object e) {
        ensureCapacity();
        elements[size++] = e;
    }

    /**
     * 修复后的 pop 方法
     * 弹出元素时清空数组中的过期引用
     */
    public Object pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        Object result = elements[--size];
        elements[size] = null; // 消除过期引用
        return result;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 确保数组容量足够
     */
    private void ensureCapacity() {
        if (elements.length == size) {
            elements = java.util.Arrays.copyOf(elements, 2 * size + 1);
        }
    }
}
