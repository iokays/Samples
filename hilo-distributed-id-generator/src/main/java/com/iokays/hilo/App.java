package com.iokays.hilo;

import com.iokays.hilo.config.DatabaseSequence;
import com.iokays.hilo.service.HiLoIdGenerator;

import java.io.InputStream;
import java.util.Properties;

/**
 * Hi/Lo 分布式ID生成器演示程序
 */
public class App {

    public static void main(String[] args) {
        try {
            // 1. 从配置文件加载步长
            Properties props = new Properties();
            try (InputStream input = App.class.getClassLoader().getResourceAsStream("hilo.properties")) {
                if (input == null) {
                    System.out.println("抱歉，无法找到 hilo.properties 文件");
                    return;
                }
                props.load(input);
            }
            long incrementSize = Long.parseLong(props.getProperty("incrementSize", "100"));

            // 2. 初始化模拟的数据库序列
            DatabaseSequence dbSequence = new DatabaseSequence();

            // 3. 创建Hi/Lo ID生成器实例
            HiLoIdGenerator idGenerator = new HiLoIdGenerator(incrementSize, dbSequence);

            System.out.printf("Hi/Lo算法演示 (步长 = %d, 从配置文件加载)\n", incrementSize);
            System.out.println("--------------------------------------------------");

            // 4. 演示ID生成过程
            int idsToGenerate = 205;
            System.out.printf("准备生成 %d 个ID...\n", idsToGenerate);

            for (int i = 0; i < idsToGenerate; i++) {
                long newId = idGenerator.getNextId();
                System.out.printf("生成ID: %-5d | ", newId);
                if ((i + 1) % 5 == 0) {
                    System.out.println();
                }
            }

            System.out.println("\n--------------------------------------------------");
            System.out.println("演示结束.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}