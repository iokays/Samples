package com.iokays.obsolete.fixed;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 修复内存泄漏的监听器实现
 * 
 * <p>使用弱引用存储监听器，确保回调能被垃圾收集。
 * 只存储弱引用，例如，仅将它们保存在 WeakHashMap 的键中。
 */
public class FixedListenerManager<T> {
    private final List<WeakReference<T>> listeners = new ArrayList<>();

    /**
     * 注册监听器（使用弱引用）
     */
    public void addListener(T listener) {
        listeners.add(new WeakReference<>(listener));
    }

    /**
     * 触发事件通知所有监听器
     * 同时清理已被垃圾回收的监听器
     */
    public void notifyAll(java.util.function.Consumer<T> action) {
        Iterator<WeakReference<T>> iterator = listeners.iterator();
        while (iterator.hasNext()) {
            WeakReference<T> ref = iterator.next();
            T listener = ref.get();
            if (listener == null) {
                // 监听器已被垃圾回收，从列表中移除
                iterator.remove();
            } else {
                action.accept(listener);
            }
        }
    }

    /**
     * 移除监听器
     */
    public void removeListener(T listener) {
        Iterator<WeakReference<T>> iterator = listeners.iterator();
        while (iterator.hasNext()) {
            WeakReference<T> ref = iterator.next();
            T existing = ref.get();
            if (existing == null || existing.equals(listener)) {
                iterator.remove();
            }
        }
    }

    public int listenerCount() {
        // 清理无效引用后返回数量
        cleanUp();
        return listeners.size();
    }

    /**
     * 清理已被垃圾回收的监听器引用
     */
    private void cleanUp() {
        listeners.removeIf(ref -> ref.get() == null);
    }

    public void clear() {
        listeners.clear();
    }
}
