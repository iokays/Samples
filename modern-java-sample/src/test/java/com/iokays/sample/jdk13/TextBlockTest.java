package com.iokays.sample.jdk13;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TextBlockTest {

    /**
     * 文本块:
     */
    @Test
    void test() {
        final var str = """
                你好，这是第一行,
                我的第二行，很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很很长。
                我是第三行
                我在最后一行
                """;
        Assertions.assertEquals(4, str.split(StringUtils.LF).length);

    }

}
