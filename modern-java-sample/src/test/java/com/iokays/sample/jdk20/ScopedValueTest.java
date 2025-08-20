package com.iokays.sample.jdk20;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 提供了一种线程安全的机制，用于在特定代码块（作用域）内共享不可变数据，
 * 特别适合替代 ThreadLocal 在虚拟线程（Virtual Threads）场景下的性能问题。
 */
class ScopedValueTest {
    // 定义 Scoped Value
    private static final ScopedValue<String> USER = ScopedValue.newInstance();

    /**
     * Scoped Values 的优势
     * 轻量级：数据仅绑定到特定代码块，不依赖线程。
     * 不可变：一旦设置，无法修改，避免竞态条件。
     * 结构化并发友好：与虚拟线程和结构化任务（StructuredTaskScope）完美配合。
     */
    @Test
    void test() {
        // 绑定值并执行任务
        ScopedValue.where(USER, "李白").run(() -> {
            Assertions.assertEquals("李白", USER.get());
            ScopedValue.where(USER, "杜甫").run( () -> Assertions.assertEquals("杜甫", USER.get()));
        });
    }

}