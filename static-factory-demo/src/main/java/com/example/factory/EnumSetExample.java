package com.example.factory;

import java.util.EnumSet;
import java.util.Set;

/**
 * 动态类型选择示例
 * 
 * 演示静态工厂方法的优势 4：
 * 返回对象的类可以根据输入参数的不同而不同
 * 
 * 以 EnumSet 为例：
 * - 枚举元素 <= 64 个：返回 RegularEnumSet（使用 long 存储）
 * - 枚举元素 > 64 个：返回 JumboEnumSet（使用 long[] 存储）
 * 
 * 客户端无需关心具体实现类，只依赖接口编程
 */
public class EnumSetExample {
    
    /**
     * 小型枚举（<= 64 个元素）
     * EnumSet.of() 将返回 RegularEnumSet 实例
     */
    public enum SmallEnum {
        A, B, C, D, E
    }
    
    /**
     * 大型枚举（> 64 个元素）
     * EnumSet.of() 将返回 JumboEnumSet 实例
     */
    public enum LargeEnum {
        // 定义超过 64 个枚举常量
        E1, E2, E3, E4, E5, E6, E7, E8, E9, E10,
        E11, E12, E13, E14, E15, E16, E17, E18, E19, E20,
        E21, E22, E23, E24, E25, E26, E27, E28, E29, E30,
        E31, E32, E33, E34, E35, E36, E37, E38, E39, E40,
        E41, E42, E43, E44, E45, E46, E47, E48, E49, E50,
        E51, E52, E53, E54, E55, E56, E57, E58, E59, E60,
        E61, E62, E63, E64, E65
    }
    
    /**
     * 演示根据参数动态选择实现类
     */
    public static void demonstrateDynamicTypeSelection() {
        System.out.println("=== 动态类型选择 ===\n");
        
        // 小型枚举：返回 RegularEnumSet
        Set<SmallEnum> smallSet = EnumSet.of(SmallEnum.A, SmallEnum.B, SmallEnum.C);
        System.out.println("1. 小型枚举集合");
        System.out.println("枚举元素数量: " + SmallEnum.values().length);
        System.out.println("返回的类名: " + smallSet.getClass().getSimpleName());
        System.out.println("预期: RegularEnumSet（使用 long 存储）\n");
        
        // 大型枚举：返回 JumboEnumSet
        Set<LargeEnum> largeSet = EnumSet.of(LargeEnum.E1, LargeEnum.E2, LargeEnum.E3);
        System.out.println("2. 大型枚举集合");
        System.out.println("枚举元素数量: " + LargeEnum.values().length);
        System.out.println("返回的类名: " + largeSet.getClass().getSimpleName());
        System.out.println("预期: JumboEnumSet（使用 long[] 存储）\n");
        
        System.out.println("3. 优势分析");
        System.out.println("- 客户端无需知道具体实现类");
        System.out.println("- 只依赖 EnumSet 接口编程");
        System.out.println("- 框架可以在未来版本中更换实现");
        System.out.println("- 不会破坏客户端代码");
    }
}
