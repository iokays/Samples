package com.example.factory;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

/**
 * ImmutableCollectionFactory 测试类
 */
@DisplayName("ImmutableCollectionFactory 测试")
class ImmutableCollectionFactoryTest {
    
    @Test
    @DisplayName("emptyList() 多次调用应该返回相同实例")
    void testEmptyListReturnsSameInstance() {
        List<String> empty1 = ImmutableCollectionFactory.emptyList();
        List<String> empty2 = ImmutableCollectionFactory.emptyList();
        
        assertSame(empty1, empty2, "多次调用 emptyList() 应该返回相同实例");
    }
    
    @Test
    @DisplayName("emptyList() 返回的列表应该为空")
    void testEmptyListIsEmpty() {
        List<String> empty = ImmutableCollectionFactory.emptyList();
        
        assertTrue(empty.isEmpty());
        assertEquals(0, empty.size());
    }
    
    @Test
    @DisplayName("unmodifiableList() 返回的列表应该是不可变的")
    void testUnmodifiableListIsImmutable() {
        List<String> mutable = new ArrayList<>();
        mutable.add("Item 1");
        mutable.add("Item 2");
        
        List<String> immutable = ImmutableCollectionFactory.unmodifiableList(mutable);
        
        // 修改原始列表应该影响不可变视图
        assertEquals(2, immutable.size());
        mutable.add("Item 3");
        assertEquals(3, immutable.size());
        
        // 尝试修改不可变列表应该抛出异常
        assertThrows(UnsupportedOperationException.class, () -> {
            immutable.add("New Item");
        });
    }
    
    @Test
    @DisplayName("unmodifiableList() 应该保持原始列表的元素")
    void testUnmodifiableListPreservesElements() {
        List<String> mutable = new ArrayList<>();
        mutable.add("Item 1");
        mutable.add("Item 2");
        
        List<String> immutable = ImmutableCollectionFactory.unmodifiableList(mutable);
        
        assertEquals(2, immutable.size());
        assertEquals("Item 1", immutable.get(0));
        assertEquals("Item 2", immutable.get(1));
    }
    
    @Test
    @DisplayName("emptyMap() 多次调用应该返回相同实例")
    void testEmptyMapReturnsSameInstance() {
        var map1 = ImmutableCollectionFactory.emptyMap();
        var map2 = ImmutableCollectionFactory.emptyMap();
        
        assertSame(map1, map2, "多次调用 emptyMap() 应该返回相同实例");
    }
}
