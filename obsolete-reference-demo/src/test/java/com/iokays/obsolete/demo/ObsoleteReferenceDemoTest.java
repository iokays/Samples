package com.iokays.obsolete.demo;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ObsoleteReferenceDemo 单元测试
 */
class ObsoleteReferenceDemoTest {

    @Test
    void testMain() {
        // 捕获标准输出
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            ObsoleteReferenceDemo.main(new String[]{});
            
            String output = outContent.toString();
            
            // 验证输出包含关键信息
            assertTrue(output.contains("过期引用内存泄漏演示"));
            assertTrue(output.contains("内存泄漏的栈实现"));
            assertTrue(output.contains("修复后的栈实现"));
            assertTrue(output.contains("警告"));
            assertTrue(output.contains("正确"));
        } finally {
            // 恢复标准输出
            System.setOut(originalOut);
        }
    }
}
