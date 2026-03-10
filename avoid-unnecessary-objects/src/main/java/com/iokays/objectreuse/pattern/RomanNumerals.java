package com.iokays.objectreuse.pattern;

import java.util.regex.Pattern;

/**
 * 演示昂贵对象的重用：通过缓存 Pattern 实例避免重复编译正则表达式。
 * 
 * <p>Pattern 编译是昂贵操作，需要将正则表达式编译为有限状态机。
 * 如果频繁调用 String.matches()，会创建大量 Pattern 实例并立即被垃圾回收。
 * 
 * <p>优化策略：将 Pattern 编译为静态常量，在多次调用中重用。
 */
public class RomanNumerals {
    
    /**
     * 缓存的 Pattern 实例，在类加载时编译一次，后续调用可重用。
     * 
     * <p>性能提升：从 1.1 微秒/次 降低到 0.17 微秒/次（提升 6.5 倍）。
     */
    private static final Pattern ROMAN = Pattern.compile(
        "^(?=.)M*(C[MD]|D?C{0,3})" +
        "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$"
    );
    
    /**
     * 错误示例：每次调用都编译正则表达式。
     * String.matches() 内部会创建 Pattern 实例，只使用一次就被丢弃。
     * 
     * @param s 待验证的字符串
     * @return 是否为罗马数字
     */
    public static boolean isRomanNumeralInefficient(String s) {
        // DON'T DO THIS! 每次调用都会编译正则表达式
        return s.matches("^(?=.)M*(C[MD]|D?C{0,3})" +
                        "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");
    }
    
    /**
     * 正确示例：重用缓存的 Pattern 实例。
     * 只在类加载时编译一次，后续调用直接使用缓存实例。
     * 
     * @param s 待验证的字符串
     * @return 是否为罗马数字
     */
    public static boolean isRomanNumeral(String s) {
        // 正确做法：重用缓存的 Pattern
        return ROMAN.matcher(s).matches();
    }
}
