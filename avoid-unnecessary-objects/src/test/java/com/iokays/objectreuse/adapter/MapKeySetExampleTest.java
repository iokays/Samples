package com.iokays.objectreuse.adapter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * MapKeySetExample 测试类。
 */
@DisplayName("Map KeySet 视图测试")
class MapKeySetExampleTest {
    
    @Test
    @DisplayName("视图对象的基本功能")
    void testKeySetBasicFunctionality() {
        MapKeySetExample example = new MapKeySetExample();
        Set<String> keySet = example.getKeySet();
        
        assertTrue(keySet.contains("one"));
        assertTrue(keySet.contains("two"));
        assertTrue(keySet.contains("three"));
        assertEquals(3, keySet.size());
    }
    
    @Test
    @DisplayName("视图对象的同步性：修改 Map 反映到视图")
    void testViewSynchronization() {
        MapKeySetExample example = new MapKeySetExample();
        Set<String> view = example.getKeySet();
        
        // 初始状态
        assertEquals(3, view.size());
        assertFalse(view.contains("four"));
        
        // 修改 Map
        example.getMap().put("four", 4);
        
        // 视图应该反映修改
        assertEquals(4, view.size());
        assertTrue(view.contains("four"));
    }
    
    @Test
    @DisplayName("视图删除操作影响原始 Map")
    void testViewRemove() {
        MapKeySetExample example = new MapKeySetExample();
        Set<String> view = example.getKeySet();
        
        // 通过视图删除元素
        assertTrue(view.remove("one"));
        
        // Map 也应该被修改
        assertFalse(example.getMap().containsKey("one"));
        assertEquals(2, example.getMap().size());
    }
    
    @Test
    @DisplayName("演示视图行为")
    void testDemonstrateViewBehavior() {
        MapKeySetExample example = new MapKeySetExample();
        
        // 这个方法内部包含断言，验证视图的行为
        assertDoesNotThrow(() -> example.demonstrateViewBehavior());
    }
    
    @Test
    @DisplayName("多个视图对象的一致性")
    void testMultipleViews() {
        MapKeySetExample example = new MapKeySetExample();
        
        // 获取两个视图
        Set<String> view1 = example.getKeySet();
        Set<String> view2 = example.getKeySet();
        
        // 两个视图的内容应该相同
        assertEquals(view1.size(), view2.size());
        assertTrue(view1.containsAll(view2));
        
        // 通过一个视图修改，另一个视图也会反映变化
        view1.remove("one");
        assertFalse(view2.contains("one"));
    }
}
