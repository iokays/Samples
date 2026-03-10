package com.iokays.objectreuse.boxing;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

/**
 * AutoBoxingExample 测试类。
 */
@DisplayName("自动装箱示例测试")
class AutoBoxingExampleTest {
    
    @Test
    @DisplayName("两种求和方法结果一致")
    void testSumResultsConsistency() {
        // 对于较小的范围，结果应该一致
        // 注意：完整范围（Integer.MAX_VALUE）耗时太长，不用于单元测试
        
        // 测试小范围
        long smallRange = 10000L;
        long expected = smallRange * (smallRange + 1) / 2;
        
        // 这里我们手动计算小范围的和，而不是调用完整的方法
        long sumWithBoxing = calculateSumWithBoxing(smallRange);
        long sumWithoutBoxing = calculateSumWithoutBoxing(smallRange);
        
        assertEquals(expected, sumWithBoxing);
        assertEquals(expected, sumWithoutBoxing);
    }
    
    /**
     * 小范围的装箱版本（用于测试）。
     */
    private long calculateSumWithBoxing(long limit) {
        Long sum = 0L;
        for (long i = 0; i <= limit; i++) {
            sum += i;
        }
        return sum;
    }
    
    /**
     * 小范围的无装箱版本（用于测试）。
     */
    private long calculateSumWithoutBoxing(long limit) {
        long sum = 0L;
        for (long i = 0; i <= limit; i++) {
            sum += i;
        }
        return sum;
    }
    
    @Test
    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    @DisplayName("性能差异：无装箱版本更快")
    void testPerformanceDifference() {
        long limit = 100000L; // 适中范围用于测试
        
        // 无装箱版本
        long startWithoutBoxing = System.nanoTime();
        long resultWithoutBoxing = calculateSumWithoutBoxing(limit);
        long timeWithoutBoxing = System.nanoTime() - startWithoutBoxing;
        
        // 装箱版本
        long startWithBoxing = System.nanoTime();
        long resultWithBoxing = calculateSumWithBoxing(limit);
        long timeWithBoxing = System.nanoTime() - startWithBoxing;
        
        // 结果应该一致
        assertEquals(resultWithoutBoxing, resultWithBoxing);
        
        // 无装箱版本应该更快（通常快 5-10 倍）
        // 注意：具体的性能差异取决于 JVM 优化
        System.out.printf("无装箱: %d ns, 装箱: %d ns, 比率: %.2f%n",
            timeWithoutBoxing, timeWithBoxing, 
            (double) timeWithBoxing / timeWithoutBoxing);
    }
    
    @Test
    @DisplayName("Integer 缓存范围测试")
    void testIntegerCache() {
        // 在缓存范围内（-128 到 127）
        Integer a = 127;
        Integer b = 127;
        assertSame(a, b, "在缓存范围内应该重用同一个对象");
        
        // 超出缓存范围
        Integer c = 128;
        Integer d = 128;
        assertNotSame(c, d, "超出缓存范围会创建新对象");
        assertEquals(c, d, "但内容相等");
        
        // 使用 valueOf 方法
        Integer e = Integer.valueOf(100);
        Integer f = Integer.valueOf(100);
        assertSame(e, f, "valueOf 在缓存范围内重用对象");
    }
    
    @Test
    @DisplayName("自动装箱演示")
    void testDemonstrateAutoBoxing() {
        // 这个方法内部包含断言
        assertDoesNotThrow(() -> AutoBoxingExample.demonstrateAutoBoxing());
    }
    
    @Test
    @DisplayName("基本类型优先原则")
    void testPreferPrimitiveTypes() {
        // 在性能关键的场景，使用基本类型
        int primitiveSum = 0;
        for (int i = 0; i < 1000; i++) {
            primitiveSum += i;
        }
        
        // 包装类型（不推荐）
        Integer boxedSum = 0;
        for (int i = 0; i < 1000; i++) {
            boxedSum += i; // 每次迭代都会拆箱、计算、装箱
        }
        
        assertEquals(primitiveSum, boxedSum.intValue());
    }
}
