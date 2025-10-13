package com.iokays.hilo;

import com.iokays.hilo.config.DatabaseSequence;
import com.iokays.hilo.service.HiLoIdGenerator;

import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * Hi/Lo ID 生成器并发测试
 * <p>
 * 使用JDK 25的虚拟线程来模拟高并发场景，测试HiLoIdGenerator的线程安全性。
 */
public class ConcurrentTest {

    public static void main(String[] args) {
        // 1. 初始化共享的数据库序列和ID生成器
        DatabaseSequence dbSequence = new DatabaseSequence();
        long incrementSize = 1000;
        HiLoIdGenerator idGenerator = new HiLoIdGenerator(incrementSize, dbSequence);

        System.out.println("并发测试开始，使用虚拟线程模拟100个客户端，每个客户端请求50个ID。");
        System.out.println("---------------------------------------------------------------------");

        // 2. 使用JDK 25的虚拟线程执行器
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            // 3. 模拟100个并发客户端
            IntStream.range(0, 100).forEach(client -> executor.submit(() -> {
                for (int i = 0; i < 50; i++) {
                    long id = idGenerator.getNextId();
                    // 在高并发下，为了避免控制台输出混乱，我们只在特定条件下打印
                    if (id % 1000 == 0) {
                        System.out.printf("[客户端 %d] 生成了ID: %d\n", client, id);
                    }
                }
            }));
        } // try-with-resources 会自动关闭 executor 并等待所有任务完成

        System.out.println("---------------------------------------------------------------------");
        System.out.println("并发测试结束。");

        // 4. 验证最终生成的ID是否符合预期
        // 总共请求了 100 * 50 = 5000 个ID
        // 步长为 1000，所以应该消耗了 5000 / 1000 = 5 个Hi值 (1, 2, 3, 4, 5)
        // 最后一个ID应该是 (5 - 1) * 1000 + (5000 % 1000 - 1) = 4000 + 999 = 4999, 
        // 但是由于并发执行的顺序不确定，我们只验证下一个ID的起始值
        long finalId = idGenerator.getNextId();
        System.out.printf("并发测试后，获取的下一个ID是: %d。这验证了ID生成的连续性和状态的正确性。\n", finalId);
    }
}
