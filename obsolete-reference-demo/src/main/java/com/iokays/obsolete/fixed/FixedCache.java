package com.iokays.obsolete.fixed;

import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * 修复内存泄漏的缓存实现
 * 
 * <p>使用 WeakHashMap 来表示缓存，
 * 这些项在过期之后自动删除。
 * 
 * <p>只有当缓存中某个项的生命周期是由外部引用到键(key)而不是值(value)决定时，
 * WeakHashMap 才有用。
 */
public class FixedCache<K, V> {
    // 使用 WeakHashMap 自动清理不再被引用的键
    private final Map<K, V> cache = new WeakHashMap<>();
    
    // 或者使用 WeakReference 来存储值
    private final Map<K, WeakReference<V>> weakValueCache = new java.util.HashMap<>();

    /**
     * 添加缓存项
     */
    public void put(K key, V value) {
        cache.put(key, value);
        weakValueCache.put(key, new WeakReference<>(value));
    }

    /**
     * 获取缓存项
     */
    public V get(K key) {
        V value = cache.get(key);
        if (value != null) {
            return value;
        }
        
        // 尝试从弱引用缓存获取
        WeakReference<V> ref = weakValueCache.get(key);
        if (ref != null) {
            value = ref.get();
            if (value == null) {
                // 弱引用已被回收，清理映射
                weakValueCache.remove(key);
            }
            return value;
        }
        return null;
    }

    public boolean contains(K key) {
        return cache.containsKey(key) || weakValueCache.containsKey(key);
    }

    public int size() {
        return cache.size();
    }

    public void clear() {
        cache.clear();
        weakValueCache.clear();
    }
}
