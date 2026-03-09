package com.example.factory;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

/**
 * BigInteger 扩展类，演示静态工厂方法的优势
 * 
 * 优势 1: 有名字的工厂方法比构造方法更具可读性
 * 构造方法: BigInteger(int bitLength, int certainty, Random rnd)
 * 工厂方法: BigInteger.probablePrime(int bitLength, Random rnd)
 */
public class BigIntegerFactory {
    
    /**
     * 返回一个可能为素数的 BigInteger
     * 
     * 对比构造方法 BigInteger(int, int, Random)：
     * - 构造方法参数含义不清晰：第一个是 bitLength，第二个是 certainty？
     * - 静态工厂方法名字明确表达了意图：probablePrime
     * 
     * @param bitLength 位数
     * @param certainty 确定性参数
     * @param rnd 随机数生成器
     * @return 可能是素数的 BigInteger
     */
    public static BigInteger probablePrime(int bitLength, int certainty, Random rnd) {
        return BigInteger.probablePrime(bitLength, rnd);
    }
    
    /**
     * 创建一个可能为素数的 BigInteger（简化版，使用默认确定性）
     * 
     * @param bitLength 位数
     * @return 可能是素数的 BigInteger
     */
    public static BigInteger probablePrime(int bitLength) {
        return BigInteger.probablePrime(bitLength, new SecureRandom());
    }
    
    /**
     * 示例：对比构造方法和工厂方法的可读性
     */
    public static void demonstrateReadability() {
        System.out.println("=== 可读性对比 ===\n");
        
        // 使用静态工厂方法 - 清晰易懂
        BigInteger prime1 = BigIntegerFactory.probablePrime(512);
        System.out.println("使用静态工厂方法:");
        System.out.println("BigIntegerFactory.probablePrime(512)");
        System.out.println("意图清晰：创建一个可能为素数的 512 位 BigInteger\n");
        
        // 假设使用构造方法 - 参数含义模糊
        // BigInteger prime2 = new BigInteger(512, 100, new SecureRandom());
        System.out.println("对比构造方法:");
        System.out.println("new BigInteger(512, 100, new SecureRandom())");
        System.out.println("参数含义模糊：第二个参数 100 代表什么？");
        System.out.println("（实际上是确定性参数，但从调用代码看不出来）\n");
    }
}
