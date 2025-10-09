package com.iokays.sample;

import java.security.AccessController;
import java.security.PrivilegedAction;

/**
 * 步骤1: API调用器
 * 
 * 这个程序唯一的目的就是调用一些JDK中已知的废弃方法。
 * 当使用特定的JFR参数运行时，它的行为将被记录下来，用于后续分析。
 */
public class ApiCaller {

    public static void main(String[] args) {
        System.out.println("API调用程序启动...");
        System.out.println("正在调用多个已废弃的JDK方法...");

        // 调用一个已弃用的构造函数 (自JDK 9起, forRemoval=false)
        new Boolean(true);

        // 调用一个已弃用的方法 (自JDK 17起, forRemoval=true)
        AccessController.doPrivileged((PrivilegedAction<Void>) () -> null);

        // 多次调用以确保JIT编译后也能被JFR记录
        for (int i = 0; i < 1000; i++) {
            new Boolean(false);
        }

        System.out.println("API调用程序结束。如果JFR已开启，记录文件应已生成。");
    }
}
