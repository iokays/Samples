package com.iokays.obsolete.leak;

import java.util.HashMap;
import java.util.Map;

/**
 * 存在内存泄漏的缓存实现
 * 
 * <p>缓存是内存泄漏的常见来源。
 * 一旦将对象引用放入缓存中，很容易忘记它的存在，
 * 并且在它变得无关紧要之后，仍然保留在缓存中。
 */
public class LeakCache<K, V> {
    private final Map<K, V> cache = new HashMap<>();

    /**
     * 添加缓存项
     */
    public void put(K key, V value) {
        cache.put(key, value);
    }

    /**
     * 获取缓存项
     */
    public V get(K key) {
        return cache.get(key);
    }

    /**
     * 存在内存泄漏：
     * 没有机制来清理不再需要的缓存项
     */
    public boolean contains(K key) {
        return cache.containsKey(key);
    }

    public int size() {
        return cache.size();
    }

    public void clear() {
        cache.clear();
    }
}
