package com.iokays.sample.jdk14;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * null point exceptions
 * NullPointerException 详细错误信息:
 */
public class NullPointerExceptionTest {

    @Test
    void test() {
        final Integer a = null;
        final Integer b = 1;

        final NullPointerException exception = Assertions.assertThrows(NullPointerException.class, () -> {
            final var r = a + b; //因为a是null所以抛出了异常, jdk14添加空指针异常的细节信息（之前是简短的错误信息）。
        });
        Assertions.assertEquals("""
                        Cannot invoke "java.lang.Integer.intValue()" because "a" is null"""
                , exception.getMessage());
    }


}
