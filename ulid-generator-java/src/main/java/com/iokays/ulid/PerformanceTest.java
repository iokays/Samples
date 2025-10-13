package com.iokays.ulid;

import com.github.f4b6a3.ulid.UlidCreator;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 性能对比测试：ULID vs UUID
 */
public class PerformanceTest {

    private static final int NUM_IDS_TO_GENERATE = 5_000_000; // 生成五百万个ID
    private static final int NUM_THREADS = 100; // 使用100个虚拟线程并发

    public static void main(String[] args) throws InterruptedException {
        System.out.println("### 性能对比测试：ULID vs UUID ###");
        System.out.printf("将在 %d 个虚拟线程上并发生成 %d 个ID，并比较耗时。\n\n", NUM_THREADS, NUM_IDS_TO_GENERATE);

        // --- UUID 性能测试 ---
        Instant uuidStartTime = Instant.now();
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < NUM_THREADS; i++) {
                executor.submit(() -> {
                    for (int j = 0; j < NUM_IDS_TO_GENERATE / NUM_THREADS; j++) {
                        UUID.randomUUID();
                    }
                });
            }
        }
        Instant uuidEndTime = Instant.now();
        Duration uuidDuration = Duration.between(uuidStartTime, uuidEndTime);
        System.out.printf("生成 %d 个 UUID 总耗时: %d 毫秒\n", NUM_IDS_TO_GENERATE, uuidDuration.toMillis());

        // --- ULID 性能测试 ---
        Instant ulidStartTime = Instant.now();
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < NUM_THREADS; i++) {
                executor.submit(() -> {
                    for (int j = 0; j < NUM_IDS_TO_GENERATE / NUM_THREADS; j++) {
                        UlidCreator.getUlid();
                    }
                });
            }
        }
        Instant ulidEndTime = Instant.now();
        Duration ulidDuration = Duration.between(ulidStartTime, ulidEndTime);
        System.out.printf("生成 %d 个 ULID 总耗时: %d 毫秒\n", NUM_IDS_TO_GENERATE, ulidDuration.toMillis());

        System.out.println("\n测试结论：在高并发场景下，ULID的生成性能通常与UUID相当或更优，同时还提供了时间排序的宝贵特性。");
    }
}
