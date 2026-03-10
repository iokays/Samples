package com.iokays.obsolete.leak;

import java.util.ArrayList;
import java.util.List;

/**
 * 存在内存泄漏的监听器实现
 * 
 * <p>监听器和回调也是内存泄漏的常见来源。
 * 如果客户端注册回调，但是没有显式地撤销注册，
 * 它们将会累积，导致内存泄漏。
 */
public class LeakListenerManager<T> {
    private final List<T> listeners = new ArrayList<>();

    /**
     * 注册监听器
     */
    public void addListener(T listener) {
        listeners.add(listener);
    }

    /**
     * 触发事件通知所有监听器
     */
    @SuppressWarnings("unchecked")
    public void notifyAll(java.util.function.Consumer<T> action) {
        for (T listener : listeners) {
            action.accept(listener);
        }
    }

    /**
     * 存在内存泄漏：
     * 如果客户端忘记移除监听器，它会一直保留在列表中
     */
    public void removeListener(T listener) {
        listeners.remove(listener);
    }

    public int listenerCount() {
        return listeners.size();
    }

    public void clear() {
        listeners.clear();
    }
}
