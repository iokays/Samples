package com.example.factory;

/**
 * Static Factory Method Demo
 * 
 * 本项目演示 Effective Java 第 1 条的核心理念：
 * 考虑使用静态工厂方法替代构造方法
 * 
 * 核心优势：
 * 1. 静态工厂方法有名字，可读性更好
 * 2. 不需要每次调用都创建新对象（实例控制）
 * 3. 可以返回其返回类型的任何子类型的对象
 * 4. 返回对象的类可以根据输入参数的不同而不同
 * 5. 在编写包含该方法的类时，返回的对象的类不需要存在
 */
public class StaticFactoryDemo {
    
    public static void main(String[] args) {
        System.out.println("=== 静态工厂方法示例 ===\n");
        
        // 示例 1: Boolean.valueOf() - 不创建新对象
        System.out.println("1. Boolean.valueOf() - 实例控制");
        Boolean b1 = Boolean.valueOf(true);
        Boolean b2 = Boolean.valueOf(true);
        System.out.println("b1 == b2: " + (b1 == b2)); // true，返回相同实例
        
        // 示例 2: BigInteger.probablePrime() - 有名字的工厂方法
        System.out.println("\n2. BigInteger.probablePrime() - 有名字的工厂方法");
        // 注意：这里使用我们自己的示例类来模拟
        
        // 示例 3: Collections.unmodifiableList() - 返回子类型
        System.out.println("\n3. 返回子类型的示例");
        // 注意：使用我们自己的示例类
        
        // 示例 4: EnumSet - 根据参数返回不同实现
        System.out.println("\n4. 根据参数返回不同实现");
        // 注意：使用我们自己的示例类
        
        System.out.println("\n=== 示例完成 ===");
    }
}
