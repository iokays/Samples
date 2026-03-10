package com.iokays.objectreuse.boxing;

/**
 * 演示自动装箱带来的性能问题和对象创建问题。
 * 
 * <p>自动装箱模糊了基本类型和包装类型的区别，
 * 但在性能敏感的场景下，无意识的自动装箱会造成严重性能损失。
 * 
 * <p>最佳实践：优先使用基本类型，避免不必要的自动装箱。
 */
public class AutoBoxingExample {
    
    /**
     * 错误示例：使用包装类型导致大量自动装箱。
     * 
     * <p>sum 被声明为 Long（包装类型），每次 += 操作都会：
     * <ol>
     *   <li>Long 自动拆箱为 long</li>
     *   <li>执行加法运算</li>
     *   <li>long 自动装箱为 Long（创建新对象）</li>
     * </ol>
     * 
     * <p>在循环中会创建约 2^31 个不必要的 Long 实例！
     * 性能：6.3 秒
     */
    public static long sumWithBoxing() {
        // DON'T DO THIS! 变量类型错误导致自动装箱
        Long sum = 0L;
        for (long i = 0; i <= Integer.MAX_VALUE; i++) {
            sum += i; // 每次迭代都会创建新的 Long 对象
        }
        return sum;
    }
    
    /**
     * 正确示例：使用基本类型避免自动装箱。
     * 
     * <p>sum 声明为 long（基本类型），不会产生任何对象创建。
     * 性能：0.59 秒（提升 10 倍以上）
     */
    public static long sumWithoutBoxing() {
        // 正确做法：使用基本类型
        long sum = 0L;
        for (long i = 0; i <= Integer.MAX_VALUE; i++) {
            sum += i; // 纯基本类型运算，无对象创建
        }
        return sum;
    }
    
    /**
     * 演示自动装箱的隐蔽性。
     * 
     * <p>虽然自动装箱让代码看起来更简洁，
     * 但在性能关键路径上会造成严重的性能损失。
     */
    public static void demonstrateAutoBoxing() {
        // 自动装箱：int -> Integer
        Integer boxed = 42; // 编译器生成：Integer.valueOf(42)
        
        // 自动拆箱：Integer -> int
        int unboxed = boxed; // 编译器生成：boxed.intValue()
        
        // 混合运算：自动拆箱
        Integer a = 100;
        Integer b = 200;
        int sum = a + b; // a 和 b 自动拆箱后相加
        
        // 注意：Integer 缓存范围是 -128 到 127
        Integer x = 127;
        Integer y = 127;
        assert x == y : "在缓存范围内，应该重用同一个对象";
        
        Integer m = 128;
        Integer n = 128;
        // assert m == n; // 这会失败！超出缓存范围，创建了不同对象
        assert m.equals(n) : "应该使用 equals 比较内容";
    }
}
