package com.iokays.obsolete.leak;

import java.util.EmptyStackException;

/**
 * 存在内存泄漏的栈实现
 * 
 * <p>当栈增长后收缩，从栈弹出的对象不会被垃圾收集，
 * 因为栈维护对这些对象的过期引用(obsolete references)。
 * 
 * <p>这是《Effective Java》第7条的经典示例。
 */
public class LeakStack {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public LeakStack() {
        elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(Object e) {
        ensureCapacity();
        elements[size++] = e;
    }

    /**
     * 存在内存泄漏的 pop 方法
     * 弹出元素时没有清空数组中的引用
     */
    public Object pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        return elements[--size];  // 内存泄漏：没有清空过期引用
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
