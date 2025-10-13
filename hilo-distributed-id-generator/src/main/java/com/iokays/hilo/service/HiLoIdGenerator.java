package com.iokays.hilo.service;

import com.iokays.hilo.config.DatabaseSequence;

/**
 * Hi/Lo 分布式ID生成器核心服务
 * <p>
 * 该类实现了Hi/Lo算法的核心逻辑。它通过与一个模拟的数据库序列（DatabaseSequence）交互，
 * 来管理高位值（Hi）和低位值（Lo），从而高效地生成全局唯一的ID。
 */
public class HiLoIdGenerator {

    private final long incrementSize; // 步长，即每个Hi值可以生成的ID数量
    private final DatabaseSequence sequence; // 数据库序列，用于获取Hi值

    private long hi; // 当前的Hi值
    private long lo; // 当前的Lo值，范围从 0 到 incrementSize - 1

    /**
     * 构造函数
     *
     * @param incrementSize 步长（一个号段的长度）
     * @param sequence      数据库序列实例
     */
    public HiLoIdGenerator(long incrementSize, DatabaseSequence sequence) {
        this.incrementSize = incrementSize;
        this.sequence = sequence;
        this.lo = incrementSize; // 初始化lo，使其等于步长，从而触发第一次获取hi值的逻辑
    }

    /**
     * 获取下一个全局唯一ID
     * <p>
     * 这是一个线程安全的方法。当多个线程同时请求ID时，通过`synchronized`关键字确保
     * 在任何时刻只有一个线程能够修改生成器的内部状态（hi和lo），从而避免了ID冲突。
     *
     * @return 生成的唯一ID
     */
    public synchronized long getNextId() {
        // 当lo达到或超过步长时，表示当前Hi值对应的ID段已用尽
        if (lo >= incrementSize) {
            // 从数据库序列中获取下一个Hi值
            this.hi = sequence.getNextHi();
            // 重置lo为0，开始新的ID段
            this.lo = 0;
            System.out.printf("\n[信息] 当前号段已用尽, 从数据库获取新的 Hi 值: %d\n", this.hi);
        }

        // 根据Hi/Lo算法公式计算ID
        // ID = (Hi - 1) * incrementSize + Lo
        long generatedId = (hi - 1) * incrementSize + lo;

        // 递增lo，为下一个ID做准备
        lo++;

        return generatedId;
    }
}
