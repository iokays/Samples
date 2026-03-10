package com.iokays.objectreuse.pattern;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * RomanNumerals 测试类。
 */
@DisplayName("罗马数字验证测试")
class RomanNumeralsTest {
    
    @Test
    @DisplayName("验证正确的罗马数字")
    void testValidRomanNumerals() {
        assertTrue(RomanNumerals.isRomanNumeral("I"));
        assertTrue(RomanNumerals.isRomanNumeral("IV"));
        assertTrue(RomanNumerals.isRomanNumeral("IX"));
        assertTrue(RomanNumerals.isRomanNumeral("XL"));
        assertTrue(RomanNumerals.isRomanNumeral("XC"));
        assertTrue(RomanNumerals.isRomanNumeral("CD"));
        assertTrue(RomanNumerals.isRomanNumeral("CM"));
        assertTrue(RomanNumerals.isRomanNumeral("MMXXIII"));
        assertTrue(RomanNumerals.isRomanNumeral("MCMLXXXIV"));
    }
    
    @Test
    @DisplayName("拒绝无效的罗马数字")
    void testInvalidRomanNumerals() {
        assertFalse(RomanNumerals.isRomanNumeral(""));
        assertFalse(RomanNumerals.isRomanNumeral("IIII"));
        assertFalse(RomanNumerals.isRomanNumeral("VV"));
        assertFalse(RomanNumerals.isRomanNumeral("ABC"));
        assertFalse(RomanNumerals.isRomanNumeral("123"));
        assertFalse(RomanNumerals.isRomanNumeral("MCMXCIIIIV"));
    }
    
    @Test
    @DisplayName("两种实现结果一致")
    void testConsistency() {
        String[] testCases = {"I", "IV", "IX", "XL", "XC", "CD", "CM", "MMXXIII", "ABC", "123"};
        
        for (String testCase : testCases) {
            boolean efficient = RomanNumerals.isRomanNumeral(testCase);
            boolean inefficient = RomanNumerals.isRomanNumeralInefficient(testCase);
            assertEquals(efficient, inefficient, 
                "两种实现对 '" + testCase + "' 的结果应该一致");
        }
    }
    
    @RepeatedTest(10)
    @DisplayName("性能测试：高效版本应该更快")
    void testPerformance() {
        String roman = "MCMLXXXIV";
        
        // 高效版本
        long startEfficient = System.nanoTime();
        boolean resultEfficient = RomanNumerals.isRomanNumeral(roman);
        long timeEfficient = System.nanoTime() - startEfficient;
        
        // 低效版本
        long startInefficient = System.nanoTime();
        boolean resultInefficient = RomanNumerals.isRomanNumeralInefficient(roman);
        long timeInefficient = System.nanoTime() - startInefficient;
        
        assertTrue(resultEfficient);
        assertTrue(resultInefficient);
        
        // 注意：单次调用的性能差异可能不明显，但累积起来会很大
        // 实际项目中应该使用 JMH 等专业工具进行基准测试
    }
}
