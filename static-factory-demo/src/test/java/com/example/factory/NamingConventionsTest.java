package com.example.factory;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.*;

/**
 * NamingConventions 测试类
 */
@DisplayName("NamingConventions 测试")
class NamingConventionsTest {
    
    @Test
    @DisplayName("from(String) 应该返回指定字符串的实例")
    void testFromString() {
        NamingConventions nc = NamingConventions.from("test");
        
        assertNotNull(nc);
        assertEquals("test", nc.getData());
    }
    
    @Test
    @DisplayName("of(String, String) 应该返回合并后的字符串实例")
    void testOfStrings() {
        NamingConventions nc = NamingConventions.of("hello", "world");
        
        assertNotNull(nc);
        assertEquals("helloworld", nc.getData());
    }
    
    @Test
    @DisplayName("valueOf(int) 应该返回数字字符串的实例")
    void testValueOfInt() {
        NamingConventions nc = NamingConventions.valueOf(42);
        
        assertNotNull(nc);
        assertEquals("42", nc.getData());
    }
    
    @Test
    @DisplayName("getInstance() 多次调用应该返回相同实例")
    void testGetInstanceReturnsSameInstance() {
        NamingConventions instance1 = NamingConventions.getInstance();
        NamingConventions instance2 = NamingConventions.getInstance();
        
        assertSame(instance1, instance2, "getInstance() 应该返回单例");
        assertEquals("singleton", instance1.getData());
    }
    
    @Test
    @DisplayName("newInstance() 多次调用应该返回不同实例")
    void testNewInstanceReturnsDifferentInstances() {
        NamingConventions instance1 = NamingConventions.newInstance();
        NamingConventions instance2 = NamingConventions.newInstance();
        
        assertNotSame(instance1, instance2, "newInstance() 应该返回新实例");
        assertEquals("new instance", instance1.getData());
        assertEquals("new instance", instance2.getData());
    }
    
    @Test
    @DisplayName("getType() 应该返回非空实例")
    void testGetType() {
        NamingConventions nc = NamingConventions.getType();
        
        assertNotNull(nc);
        assertEquals("type from factory", nc.getData());
    }
    
    @Test
    @DisplayName("newType() 应该返回新实例")
    void testNewType() {
        NamingConventions nc = NamingConventions.newType();
        
        assertNotNull(nc);
        assertEquals("new type from factory", nc.getData());
    }
    
    @Test
    @DisplayName("type() 应该返回实例")
    void testType() {
        NamingConventions nc = NamingConventions.type();
        
        assertNotNull(nc);
        assertEquals("type", nc.getData());
    }
}
