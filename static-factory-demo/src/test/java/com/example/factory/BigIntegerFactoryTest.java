package com.example.factory;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * BigIntegerFactory 测试类
 */
@DisplayName("BigIntegerFactory 测试")
class BigIntegerFactoryTest {
    
    @Test
    @DisplayName("probablePrime(int bitLength) 应该返回指定位数的素数")
    void testProbablePrimeWithBitLength() {
        BigInteger prime = BigIntegerFactory.probablePrime(64);
        
        assertNotNull(prime);
        assertEquals(64, prime.bitLength());
        assertTrue(prime.isProbablePrime(100));
    }
    
    @Test
    @DisplayName("probablePrime(int, Random) 应该返回可能为素数的数")
    void testProbablePrimeWithRandom() {
        SecureRandom random = new SecureRandom();
        BigInteger prime = BigIntegerFactory.probablePrime(128, 100, random);
        
        assertNotNull(prime);
        assertEquals(128, prime.bitLength());
        assertTrue(prime.isProbablePrime(100));
    }
    
    @Test
    @DisplayName("多次调用 probablePrime 应该返回不同的值")
    void testProbablePrimeReturnsDifferentValues() {
        BigInteger prime1 = BigIntegerFactory.probablePrime(256);
        BigInteger prime2 = BigIntegerFactory.probablePrime(256);
        
        assertNotEquals(prime1, prime2);
    }
}
