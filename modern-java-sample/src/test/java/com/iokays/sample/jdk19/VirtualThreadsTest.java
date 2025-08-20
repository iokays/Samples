package com.iokays.sample.jdk19;

import io.vavr.control.Try;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 虚拟线程？
 * 轻量级线程：由 JVM 管理，而非操作系统（OS），创建和切换成本极低。
 * 数量优势：可轻松创建数百万个虚拟线程，而传统 OS 线程（平台线程）通常限制在数千个。
 * 阻塞无代价：虚拟线程在阻塞（如 I/O 操作）时会自动挂起，释放底层线程资源。
 */
public class VirtualThreadsTest {

    @Test
    void testVirtualThreads() {
        // 方式1：直接启动
        Thread virtualThread = Thread.startVirtualThread(() -> System.out.println("Hello from virtual thread!"));
        Assertions.assertTrue(virtualThread.isVirtual());

        // 方式2：使用 Builder
        final Thread virtualThread2 = Thread.ofVirtual().name("my-virtual-thread").start(() -> System.out.println("Virtual thread running"));
        Assertions.assertTrue(virtualThread2.isVirtual());

        // 等待两个虚拟线程结束
        Try.run(() -> {
            virtualThread.join();  // 阻塞直到 virtualThread 结束
            virtualThread2.join(); // 阻塞直到 virtualThread2 结束
        });

        // 检查线程是否已终止
        Assertions.assertFalse(virtualThread.isAlive(), "virtualThread 应该已结束");
        Assertions.assertFalse(virtualThread2.isAlive(), "virtualThread2 应该已结束");
        System.out.println("所有虚拟线程已终止！");
    }

    @Test
    void testExecutor() throws InterruptedException {
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            executor.submit(() -> {
                Assertions.assertTrue(Thread.currentThread().isVirtual()); // 当前线程是虚拟线程
                System.out.println("Hello from virtual thread 1!");
            });
            executor.submit(() ->
            {
                Assertions.assertTrue(Thread.currentThread().isVirtual());
                System.out.println("Hello from virtual thread 2!");
            });
            executor.shutdown();
            // 等待所有任务完成
            Assertions.assertTrue(executor.awaitTermination(1, TimeUnit.MINUTES));
            System.out.println("所有虚拟线程已终止！");}
    }

}
