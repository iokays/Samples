package com.iokays.hilo.config;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 模拟数据库序列
 * <p>
 * 这个类使用 AtomicLong 来模拟一个线程安全的、全局唯一的序列提供者，用于生成 "Hi" 值。
 * 在实际的分布式系统中，这通常由一个独立的数据库表、序列（Sequence）或者类似Redis的集中式服务来承担。
 */
public class DatabaseSequence {

    // 使用原子长整型确保线程安全和原子性操作，初始值为0。
    private final AtomicLong sequence = new AtomicLong(0);

    /**
     * 获取下一个序列值。
     * <p>
     * 每次调用，该方法都会原子性地将当前值加1，并返回新值。
     * 这模拟了从数据库获取一个唯一的、递增的 "Hi" 值的过程。
     *
     * @return 下一个唯一的 "Hi" 值。
     */
    public long getNextHi() {
        return sequence.incrementAndGet();
    }
}
