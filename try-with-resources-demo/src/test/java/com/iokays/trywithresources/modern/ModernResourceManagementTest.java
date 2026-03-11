package com.iokays.trywithresources.modern;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 测试 try-with-resources 方式的资源管理
 */
class ModernResourceManagementTest {
    
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
        String firstLine = ModernResourceManagement.firstLineOfFile(testFile.toString());
        assertEquals("First line", firstLine);
    }
    
    @Test
    void testFirstLineOfFileWithDefault() {
        String result = ModernResourceManagement.firstLineOfFile("nonexistent.txt", "default");
        assertEquals("default", result);
    }
    
    @Test
    void testCopy() throws IOException {
        ModernResourceManagement.copy(testFile.toString(), destFile.toString());
        
        assertTrue(Files.exists(destFile));
        String content = Files.readString(destFile);
        assertEquals("First line\nSecond line\nThird line", content);
    }
    
    @Test
    void testExceptionSuppression() {
        // 在 try-with-resources 中，try 块的异常是主要异常
        // close() 的异常会被抑制
        IOException exception = assertThrows(IOException.class, () -> {
            ModernResourceManagement.demonstrateExceptionSuppression(testFile.toString());
        });
        
        // 主要异常是 try 块抛出的
        assertEquals("Primary exception from readLine", exception.getMessage());
        
        // 即使 close() 抛出异常，也能通过 getSuppressed() 访问
        // 注意：BufferedReader 的 close() 通常不会抛出异常
        // 所以这个测试主要验证异常抑制机制的概念
    }
    
    @Test
    void testFileNotFound() {
        assertThrows(IOException.class, () -> {
            ModernResourceManagement.firstLineOfFile("nonexistent.txt");
        });
    }
    
    @Test
    void testPrintSuppressedExceptions() {
        IOException primary = new IOException("Primary exception");
        primary.addSuppressed(new IOException("Suppressed 1"));
        primary.addSuppressed(new IOException("Suppressed 2"));
        
        // 这个方法只是打印，我们验证它不会抛出异常
        assertDoesNotThrow(() -> ModernResourceManagement.printSuppressedExceptions(primary));
    }
}
