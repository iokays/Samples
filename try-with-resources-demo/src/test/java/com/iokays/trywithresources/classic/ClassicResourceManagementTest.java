package com.iokays.trywithresources.classic;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 测试传统 try-finally 方式的资源管理
 */
class ClassicResourceManagementTest {
    
    @TempDir
    Path tempDir;
    
    private Path testFile;
    private Path destFile;
    
    @BeforeEach
    void setUp() throws IOException {
        testFile = tempDir.resolve("test.txt");
        Files.writeString(testFile, "First line\nSecond line\nThird line");
        
        destFile = tempDir.resolve("dest.txt");
    }
    
    @Test
    void testFirstLineOfFile() throws IOException {
        String firstLine = ClassicResourceManagement.firstLineOfFile(testFile.toString());
        assertEquals("First line", firstLine);
    }
    
    @Test
    void testCopy() throws IOException {
        ClassicResourceManagement.copy(testFile.toString(), destFile.toString());
        
        assertTrue(Files.exists(destFile));
        String content = Files.readString(destFile);
        assertEquals("First line\nSecond line\nThird line", content);
    }
    
    @Test
    void testExceptionSuppression() {
        // 在传统方式中，finally 块的异常会覆盖 try 块的异常
        // 这里我们期望捕获到的是 finally 块抛出的异常
        IOException exception = assertThrows(IOException.class, () -> {
            ClassicResourceManagement.demonstrateExceptionSuppression(testFile.toString());
        });
        
        // 传统方式下，我们只能看到 finally 块的异常
        assertEquals("Secondary exception from close", exception.getMessage());
        
        // 而原始的 try 块异常被覆盖了，无法访问
        assertEquals(0, exception.getSuppressed().length);
    }
    
    @Test
    void testFileNotFound() {
        assertThrows(IOException.class, () -> {
            ClassicResourceManagement.firstLineOfFile("nonexistent.txt");
        });
    }
}
