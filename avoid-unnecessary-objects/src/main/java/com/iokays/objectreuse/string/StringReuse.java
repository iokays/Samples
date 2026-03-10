package com.iokays.objectreuse.string;

/**
 * 演示字符串对象的重用，避免创建不必要的 String 实例。
 * 
 * <p>核心原则：不可变对象总是可以被重用。
 * 
 * <p>示例对比：
 * <ul>
 *   <li>错误做法：String s = new String("bikini"); // 每次创建新对象</li>
 *   <li>正确做法：String s = "bikini"; // 重用字符串常量池中的对象</li>
 * </ul>
 */
public class StringReuse {
    
    /**
     * 错误示例：每次调用都创建新的 String 对象。
     * 如果在循环或频繁调用的方法中使用，会创建大量不必要的实例。
     * 
     * @param value 字符串值
     * @return 新创建的 String 对象（不推荐）
     */
    public static String createUnnecessarily(String value) {
        // DON'T DO THIS! 每次都创建新对象
        return new String(value);
    }
    
    /**
     * 正确示例：重用字符串常量池中的对象。
     * 字符串字面量会被 JVM 自动放入字符串常量池，可以被重用。
     * 
     * @param value 字符串值
     * @return 重用的 String 对象（推荐）
     */
    public static String reuseCorrectly(String value) {
        // 正确做法：直接返回参数或使用字面量
        return value;
    }
    
    /**
     * 演示字符串字面量的重用。
     * JVM 保证相同的字符串字面量指向同一个对象。
     */
    public static void demonstrateLiteralReuse() {
        String s1 = "bikini";
        String s2 = "bikini";
        
        // s1 和 s2 指向同一个对象
        assert s1 == s2 : "字符串字面量应该被重用";
        assert s1.equals(s2) : "内容相等";
    }
}
