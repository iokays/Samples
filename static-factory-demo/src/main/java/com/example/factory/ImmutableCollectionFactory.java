package com.example.factory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 不可变集合工厂
 * 
 * 演示静态工厂方法的优势 2 和 3：
 * - 不需要每次调用都创建新对象（实例控制）
 * - 可以返回其返回类型的任何子类型的对象
 */
public class ImmutableCollectionFactory {
    
    // 预构建的空集合实例（单例模式）
    private static final List<Object> EMPTY_LIST = Collections.emptyList();
    private static final Map<Object, Object> EMPTY_MAP = Collections.emptyMap();
    
    /**
     * 返回空的不可变列表
     * 每次调用返回同一个实例，避免创建不必要的对象
     * 
     * @param <T> 列表元素类型
     * @return 空的不可变列表
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> emptyList() {
        return (List<T>) EMPTY_LIST;
    }
    
    /**
     * 返回空的不可变映射
     * 每次调用返回同一个实例，避免创建不必要的对象
     * 
     * @param <K> 键类型
     * @param <V> 值类型
     * @return 空的不可变映射
     */
    @SuppressWarnings("unchecked")
    public static <K, V> Map<K, V> emptyMap() {
        return (Map<K, V>) EMPTY_MAP;
    }
    
    /**
     * 返回不可变列表
     * 返回的是包装类的实例，而不是原始列表
     * 
     * @param <T> 列表元素类型
     * @param list 原始列表
     * @return 不可变列表
     */
    public static <T> List<T> unmodifiableList(List<? extends T> list) {
        return Collections.unmodifiableList(list);
    }
    
    /**
     * 演示实例控制和子类型返回
     */
    public static void demonstrate() {
        System.out.println("=== 实例控制和子类型返回 ===\n");
        
        // 实例控制：多次调用返回相同实例
        System.out.println("1. 实例控制：避免创建重复对象");
        List<String> empty1 = emptyList();
        List<String> empty2 = emptyList();
        System.out.println("empty1 == empty2: " + (empty1 == empty2));
        System.out.println("节省内存：多次调用 emptyList() 返回同一实例\n");
        
        // 子类型返回：返回的是包装类实例
        System.out.println("2. 子类型返回：隐藏实现细节");
        List<String> mutable = new ArrayList<>();
        mutable.add("Item");
        List<String> immutable = unmodifiableList(mutable);
        System.out.println("可变列表类名: " + mutable.getClass().getName());
        System.out.println("不可变列表类名: " + immutable.getClass().getName());
        System.out.println("返回的是包装类，客户端无需知道具体实现\n");
        
        // 尝试修改不可变列表
        System.out.println("3. 不可变性验证");
        try {
            immutable.add("New Item");
            System.out.println("修改成功（不应该到这里）");
        } catch (UnsupportedOperationException e) {
            System.out.println("捕获异常: UnsupportedOperationException");
            System.out.println("无法修改不可变列表，保证线程安全\n");
        }
    }
}
