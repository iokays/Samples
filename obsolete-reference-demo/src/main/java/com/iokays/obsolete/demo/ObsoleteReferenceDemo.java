package com.iokays.obsolete.demo;

import com.iokays.obsolete.fixed.FixedStack;
import com.iokays.obsolete.leak.LeakStack;

/**
 * 演示过期引用导致的内存泄漏问题
 * 
 * <p>本示例展示《Effective Java》第7条的核心内容：
 * 如何识别和消除过期对象引用。
 */
public class ObsoleteReferenceDemo {

    public static void main(String[] args) {
        System.out.println("=== 过期引用内存泄漏演示 ===\n");

        demonstrateStackMemoryLeak();
        demonstrateFixedStack();
    }

    /**
     * 演示栈的内存泄漏问题
     */
    private static void demonstrateStackMemoryLeak() {
        System.out.println("1. 内存泄漏的栈实现：");
        
        LeakStack stack = new LeakStack();
        
        // 压入大量对象
        for (int i = 0; i < 100; i++) {
            stack.push(new Object());
        }
        
        System.out.println("   压入 100 个对象后，栈大小: " + stack.size());
        
        // 弹出所有对象
        while (!stack.isEmpty()) {
            stack.pop();
        }
        
        System.out.println("   弹出所有对象后，栈大小: " + stack.size());
        System.out.println("   ⚠️ 警告：虽然栈已空，但数组中仍保留着100个过期引用！");
        System.out.println("   这些对象无法被垃圾回收，造成内存泄漏。\n");
    }

    /**
     * 演示修复后的栈实现
     */
    private static void demonstrateFixedStack() {
        System.out.println("2. 修复后的栈实现：");
        
        FixedStack stack = new FixedStack();
        
        // 压入大量对象
        for (int i = 0; i < 100; i++) {
            stack.push(new Object());
        }
        
        System.out.println("   压入 100 个对象后，栈大小: " + stack.size());
        
        // 弹出所有对象
        while (!stack.isEmpty()) {
            stack.pop();
        }
        
        System.out.println("   弹出所有对象后，栈大小: " + stack.size());
        System.out.println("   ✅ 正确：弹出时已清空过期引用，对象可被垃圾回收。\n");
    }
}
