package com.iokays.objectreuse.adapter;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 演示适配器（视图）模式中的对象重用。
 * 
 * <p>适配器是没有状态的代理对象，对于同一个支持对象，
 * 不需要创建多个适配器实例。
 * 
 * <p>经典示例：Map.keySet() 返回的是视图对象，多次调用返回功能相同的实例。
 */
public class MapKeySetExample {
    
    private final Map<String, Integer> map;
    
    public MapKeySetExample() {
        this.map = new HashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
    }
    
    /**
     * 演示 keySet() 的视图重用特性。
     * 
     * <p>虽然看似每次调用都创建新的 Set 实例，
     * 但实际上返回的是基于同一个 Map 的视图。
     * 
     * @return keySet 视图对象
     */
    public Set<String> getKeySet() {
        return map.keySet();
    }
    
    /**
     * 演示视图对象的特性：对 Map 的修改会反映到所有视图上。
     */
    public void demonstrateViewBehavior() {
        Set<String> view1 = map.keySet();
        Set<String> view2 = map.keySet();
        
        // 虽然 view1 和 view2 可能是不同的对象，
        // 但它们都指向同一个 Map，因此功能完全相同
        
        // 修改 Map
        map.put("four", 4);
        
        // 两个视图都能看到新添加的 key
        assert view1.contains("four") : "view1 应该包含新添加的 key";
        assert view2.contains("four") : "view2 应该包含新添加的 key";
        
        // 通过视图删除元素
        view1.remove("one");
        
        // 两个视图和原始 Map 都会反映这个修改
        assert !map.containsKey("one") : "Map 应该反映视图的修改";
        assert !view2.contains("one") : "view2 应该反映 view1 的修改";
    }
    
    /**
     * 获取内部 Map（用于演示）。
     */
    public Map<String, Integer> getMap() {
        return map;
    }
}
