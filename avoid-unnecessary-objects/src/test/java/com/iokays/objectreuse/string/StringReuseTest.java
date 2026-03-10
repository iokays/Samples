package com.iokays.objectreuse.string;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

/**
 * StringReuse 测试类。
 */
@DisplayName("字符串重用测试")
class StringReuseTest {
    
    @Test
    @DisplayName("错误示例：new String() 创建不同对象")
    void testCreateUnnecessarily() {
        String original = "test";
        String created = StringReuse.createUnnecessarily(original);
        
        // 内容相等
        assertEquals(original, created);
        
        // 但是不是同一个对象（这是问题所在）
        assertNotSame(original, created, "new String() 会创建新对象");
    }
    
    @Test
    @DisplayName("正确示例：直接重用参数")
    void testReuseCorrectly() {
        String original = "test";
        String reused = StringReuse.reuseCorrectly(original);
        
        // 内容相等
        assertEquals(original, reused);
        
        // 是同一个对象（推荐做法）
        assertSame(original, reused, "应该重用同一个对象");
    }
    
    @Test
    @DisplayName("字符串字面量重用")
    void testDemonstrateLiteralReuse() {
        // 这个测试验证字符串字面量的重用机制
        // 如果断言失败，会抛出 AssertionError
        assertDoesNotThrow(() -> StringReuse.demonstrateLiteralReuse());
    }
    
    @Test
    @DisplayName("多次调用 new String() 创建不同对象")
    void testMultipleCreations() {
        String value = "bikini";
        String s1 = StringReuse.createUnnecessarily(value);
        String s2 = StringReuse.createUnnecessarily(value);
        String s3 = StringReuse.createUnnecessarily(value);
        
        // 所有对象内容相同
        assertEquals(s1, s2);
        assertEquals(s2, s3);
        
        // 但都是不同的对象（浪费内存）
        assertNotSame(s1, s2);
        assertNotSame(s2, s3);
        assertNotSame(s1, s3);
    }
}
