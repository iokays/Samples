package com.iokays.trywithresources.modern;

import java.io.*;

/**
 * 使用 try-with-resources 语句管理资源
 * 展示现代方式的优势
 */
public class ModernResourceManagement {
    
    private static final int BUFFER_SIZE = 8192;
    
    /**
     * 读取文件的第一行 - try-with-resources 方式
     * 优势：代码简洁，自动关闭资源
     */
    public static String firstLineOfFile(String path) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            return br.readLine();
        }
    }
    
    /**
     * 复制文件 - try-with-resources 方式
     * 优势：多个资源在一个 try 语句中管理，代码清晰
     */
    public static void copy(String src, String dst) throws IOException {
        try (InputStream in = new FileInputStream(src);
             OutputStream out = new FileOutputStream(dst)) {
            byte[] buf = new byte[BUFFER_SIZE];
            int n;
            while ((n = in.read(buf)) >= 0) {
                out.write(buf, 0, n);
            }
        }
    }
    
    /**
     * 演示异常抑制机制
     * try-with-resources 会保留主要异常，抑制次要异常
     */
    public static String demonstrateExceptionSuppression(String path) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            if (true) {
                throw new IOException("Primary exception from readLine");
            }
            return br.readLine();
        }
        // 即使 close() 抛出异常，也会被抑制，保留主要异常
    }
    
    /**
     * 带有 catch 子句的 try-with-resources
     * 优势：可以优雅地处理异常，返回默认值
     */
    public static String firstLineOfFile(String path, String defaultVal) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            return br.readLine();
        } catch (IOException e) {
            return defaultVal;
        }
    }
    
    /**
     * 获取被抑制的异常
     * 展示如何访问被抑制的异常信息
     */
    public static void printSuppressedExceptions(IOException primary) {
        Throwable[] suppressed = primary.getSuppressed();
        System.out.println("Primary exception: " + primary.getMessage());
        for (int i = 0; i < suppressed.length; i++) {
            System.out.println("Suppressed exception " + (i + 1) + ": " + suppressed[i].getMessage());
        }
    }
}
