package com.example.factory;

/**
 * 静态工厂方法示例
 * 
 * 演示 Effective Java 第 1 条：考虑使用静态工厂方法替代构造方法
 */
public class App {
    
    public static void main(String[] args) {
        System.out.println("=".repeat(60));
        System.out.println("静态工厂方法示例");
        System.out.println("Effective Java Item 1: Consider Static Factory Methods Instead of Constructors");
        System.out.println("=".repeat(60));
        System.out.println();
        
        // 示例 1: Boolean.valueOf() - 实例控制
        System.out.println("\n【示例 1】实例控制 - Boolean.valueOf()");
        Boolean b1 = Boolean.valueOf(true);
        Boolean b2 = Boolean.valueOf(true);
        System.out.println("b1 == b2: " + (b1 == b2));
        System.out.println("说明: 多次调用返回相同实例，避免创建不必要的对象");
        System.out.println("底层实现: return b ? Boolean.TRUE : Boolean.FALSE;");
        
        // 示例 2: 可读性优势
        System.out.println("\n" + "=".repeat(60));
        System.out.println("【示例 2】可读性优势 - 有名字的工厂方法");
        BigIntegerFactory.demonstrateReadability();
        
        // 示例 3: 实例控制和子类型返回
        System.out.println("\n" + "=".repeat(60));
        ImmutableCollectionFactory.demonstrate();
        
        // 示例 4: 动态类型选择
        System.out.println("\n" + "=".repeat(60));
        EnumSetExample.demonstrateDynamicTypeSelection();
        
        // 示例 5: 服务提供者框架
        System.out.println("\n" + "=".repeat(60));
        ServiceProviderExample.demonstrateServiceProvider();
        
        // 示例 6: 命名规范
        System.out.println("\n" + "=".repeat(60));
        NamingConventions.demonstrateNamingConventions();
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("示例执行完成");
        System.out.println("=".repeat(60));
    }
}
