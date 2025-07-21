package com.iokays.sample.core;

import io.vavr.collection.Stream;
import io.vavr.control.Try;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;


@Slf4j
@SpringBootTest
class LockApplicationServiceTest {

    @Resource
    private LockApplicationService service;

    @Test
    void test() {
        final var times = new ArrayList<Long>();
        Runnable runnable = () -> {
            times.add(System.currentTimeMillis());
            Try.run(() -> Thread.sleep(100));
            times.add(System.currentTimeMillis());
        };

        final List<CompletableFuture<Void>> futures = new ArrayList<>();

        // 批量发送
        Stream.range(0, 100).forEach(i -> {
            CompletableFuture<Void> future = CompletableFuture.runAsync(
                    () -> service.send(new LockApplicationService.Message(1, "Hello"), runnable)
            );
            futures.add(future);
        });

        // 等待所有任务完成
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

        // 验证升序, 就能说明分布式锁生效了
        Assertions.assertTrue(IntStream.range(0, times.size() - 1).allMatch(i -> times.get(i) <= times.get(i + 1))
                , "List is not in ascending order"
        );

    }

}