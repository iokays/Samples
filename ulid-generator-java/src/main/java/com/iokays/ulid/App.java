package com.iokays.ulid;

import com.github.f4b6a3.ulid.Ulid;
import com.github.f4b6a3.ulid.UlidCreator;

/**
 * ULID (Universally Unique Lexicographically Sortable Identifier) 生成器演示程序
 */
public class App {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("### 1. 标准ULID生成演示 ###");
        // 生成一个标准的ULID
        Ulid standardUlid = UlidCreator.getUlid();
        System.out.println("生成一个标准的ULID: " + standardUlid);
        System.out.println("ULID的字符串表示: " + standardUlid.toString());
        System.out.println("ULID对应的UUID表示: " + standardUlid.toUuid());
        System.out.println("ULID中包含的时间戳: " + standardUlid.getTime());

        System.out.println("\n--------------------------------------------------\n");

        System.out.println("### 2. 单调ULID生成演示 ###");
        System.out.println("单调ULID确保在同一毫秒内生成的ID也是严格递增的，适合需要严格排序的场景。");
        System.out.println("将在10毫秒内快速生成5个单调ULID：");

        // 获取一个单调ULID生成器实例
        // 注意：在实际应用中，应该在服务级别或线程级别共享此生成器实例，以保证单调性
        for (int i = 0; i < 5; i++) {
            // 快速连续调用以模拟在同一毫秒内生成
            Ulid monotonicUlid = UlidCreator.getMonotonicUlid();
            System.out.printf("生成的单调ULID %d: %s\n", i + 1, monotonicUlid);
        }
        // 稍微等待，以确保后续生成的ID在不同的毫秒
        Thread.sleep(10);
        System.out.println("\n(等待10ms后...)");
        Ulid nextMonotonicUlid = UlidCreator.getMonotonicUlid();
        System.out.println("新毫秒生成的单调ULID: " + nextMonotonicUlid);

        System.out.println("\n--------------------------------------------------\n");

        System.out.println("### 3. ULID的可排序性演示 ###");
        Ulid ulid1 = UlidCreator.getUlid();
        Thread.sleep(10); // 确保时间戳不同
        Ulid ulid2 = UlidCreator.getUlid();
        Thread.sleep(10);
        Ulid ulid3 = UlidCreator.getUlid();

        System.out.println("生成顺序: ulid1, ulid2, ulid3");
        System.out.println("ulid1: " + ulid1);
        System.out.println("ulid2: " + ulid2);
        System.out.println("ulid3: " + ulid3);
        // 验证其字符串表示的排序结果与生成时间顺序一致
        boolean isSorted = ulid1.toString().compareTo(ulid2.toString()) < 0 && ulid2.toString().compareTo(ulid3.toString()) < 0;
        System.out.println("\n按字符串比较，ULID是否与生成时间顺序一致? " + isSorted);

        System.out.println("\n演示结束.");
    }
}
