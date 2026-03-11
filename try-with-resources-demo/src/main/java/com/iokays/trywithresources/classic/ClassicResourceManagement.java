package com.iokays.trywithresources.classic;

import java.io.*;

/**
 * 使用传统的 try-finally 语句管理资源
 * 展示传统方式存在的问题
 */
public class ClassicResourceManagement {
    
    private static final int BUFFER_SIZE = 8192;
    
    /**
     * 读取文件的第一行 - try-finally 方式
     * 问题：代码冗长，容易出错
     */
    public static String firstLineOfFile(String path) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        try {
            return br.readLine();
        } finally {
            br.close();
        }
    }
    
    /**
     * 复制文件 - try-finally 方式
     * 问题：嵌套的 try-finally 使代码难以阅读
     */
    public static void copy(String src, String dst) throws IOException {
        InputStream in = new FileInputStream(src);
        try {
            OutputStream out = new FileOutputStream(dst);
            try {
                byte[] buf = new byte[BUFFER_SIZE];
                int n;
                while ((n = in.read(buf)) >= 0) {
                    out.write(buf, 0, n);
                }
            } finally {
                out.close();
            }
        } finally {
            in.close();
        }
    }
    
    /**
     * 演示异常覆盖问题
     * 当 try 块和 finally 块都抛出异常时，finally 的异常会覆盖 try 的异常
     */
    public static String demonstrateExceptionSuppression(String path) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        try {
            // 假设这里抛出异常
            if (true) {
                throw new IOException("Primary exception from readLine");
            }
            return br.readLine();
        } finally {
            // finally 块中的异常会覆盖 try 块中的异常
            br.close();
            throw new IOException("Secondary exception from close");
        }
    }
}
