package com.iokays.cleaner.bad;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 错误示例：使用 finalize() 方法
 * 
 * 这个示例展示了 finalize() 的各种问题：
 * 1. 执行时间不可预测
 * 2. 性能损失严重（创建和销毁慢 50 倍）
 * 3. 未捕获异常会被忽略
 * 4. 存在安全漏洞（finalizer 攻击）
 * 
 * @deprecated 使用 finalize() 是危险的做法，请使用 AutoCloseable 代替
 */
@Deprecated(since = "9", forRemoval = false)
public class FinalizerDemo {
    private static final AtomicInteger instanceCount = new AtomicInteger(0);
    private static final AtomicInteger finalizedCount = new AtomicInteger(0);
    
    private final int id;
    private final long creationTime;
    
    public FinalizerDemo() {
        this.id = instanceCount.incrementAndGet();
        this.creationTime = System.currentTimeMillis();
        System.out.println("[FinalizerDemo] 创建实例 #" + id);
    }
    
    /**
     * 错误做法：使用 finalize 方法
     * 
     * 问题：
     * 1. 不保证一定会被调用
     * 2. 不保证何时被调用
     * 3. 未捕获的异常会被静默忽略
     * 4. 阻碍垃圾回收，导致性能问题
     */
    @Override
    @Deprecated(since = "9")
    protected void finalize() throws Throwable {
        try {
            int finalized = finalizedCount.incrementAndGet();
            long elapsed = System.currentTimeMillis() - creationTime;
            System.out.println("[FinalizerDemo] 终结实例 #" + id + 
                             " (耗时: " + elapsed + "ms, 已终结: " + finalized + "/" + instanceCount.get() + ")");
            
            // 模拟异常情况：finalize 中的异常会被静默忽略
            if (id % 10 == 0) {
                throw new RuntimeException("finalize() 中未捕获的异常会被静默忽略！");
            }
        } finally {
            super.finalize();
        }
    }
    
    public int getId() {
        return id;
    }
    
    public static int getInstanceCount() {
        return instanceCount.get();
    }
    
    public static int getFinalizedCount() {
        return finalizedCount.get();
    }
    
    public static void reset() {
        instanceCount.set(0);
        finalizedCount.set(0);
    }
}
