package com.iokays.obsolete.fixed;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * FixedCache 单元测试
 */
class FixedCacheTest {

    private FixedCache<String, Object> cache;

    @BeforeEach
    void setUp() {
        cache = new FixedCache<>();
    }

    @Test
    void testPutAndGet() {
        String key = "test";
        Object value = new Object();

        cache.put(key, value);

        assertTrue(cache.contains(key));
        assertSame(value, cache.get(key));
    }

    @Test
    void testMultiplePuts() {
        for (int i = 0; i < 100; i++) {
            cache.put("key" + i, "value" + i);
        }

        assertEquals(100, cache.size());

        // 验证所有缓存项都能正确获取
        for (int i = 0; i < 100; i++) {
            assertEquals("value" + i, cache.get("key" + i));
        }
    }

    @Test
    void testClear() {
        cache.put("key1", "value1");
        cache.put("key2", "value2");

        assertEquals(2, cache.size());

        cache.clear();

        assertEquals(0, cache.size());
        assertNull(cache.get("key1"));
        assertNull(cache.get("key2"));
    }

    @Test
    void testOverwrite() {
        cache.put("key", "value1");
        cache.put("key", "value2");

        assertEquals(1, cache.size());
        assertEquals("value2", cache.get("key"));
    }

    @Test
    void testNotFound() {
        assertNull(cache.get("nonexistent"));
        assertFalse(cache.contains("nonexistent"));
    }
}
