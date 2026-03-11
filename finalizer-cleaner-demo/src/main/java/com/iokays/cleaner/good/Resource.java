package com.iokays.cleaner.good;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 正确示例：使用 AutoCloseable 实现资源管理
 * 
 * 这是推荐的做法：
 * 1. 资源释放时间可预测
 * 2. 性能最优（比 finalize 快 50 倍）
 * 3. 异常处理清晰
 * 4. 配合 try-with-resources 使用安全可靠
 */
public class Resource implements AutoCloseable {
    private static final AtomicInteger instanceCount = new AtomicInteger(0);
    private static final AtomicInteger closedCount = new AtomicInteger(0);
    
    private final int id;
    private boolean closed = false;
    private final long creationTime;
    
    public Resource() {
        this.id = instanceCount.incrementAndGet();
        this.creationTime = System.currentTimeMillis();
        System.out.println("[Resource] 创建资源 #" + id);
    }
    
    /**
     * 执行某些操作
     */
    public void doSomething() {
        if (closed) {
            throw new IllegalStateException("资源已关闭，无法使用");
        }
        System.out.println("[Resource] 资源 #" + id + " 正在执行操作...");
    }
    
    /**
     * 正确做法：显式关闭资源
     * 
     * 优点：
     * 1. 调用时间确定（由客户端控制）
     * 2. 异常处理清晰
     * 3. 可以多次调用（幂等）
     * 4. 配合 try-with-resources 自动调用
     */
    @Override
    public void close() {
        if (closed) {
            return; // 幂等性：多次调用安全
        }
        closed = true;
        int closed = closedCount.incrementAndGet();
        long elapsed = System.currentTimeMillis() - creationTime;
        System.out.println("[Resource] 关闭资源 #" + id + 
                         " (耗时: " + elapsed + "ms, 已关闭: " + closed + "/" + instanceCount.get() + ")");
    }
    
    public int getId() {
        return id;
    }
    
    public boolean isClosed() {
        return closed;
    }
    
    public static int getInstanceCount() {
        return instanceCount.get();
    }
    
    public static int getClosedCount() {
        return closedCount.get();
    }
    
    public static void reset() {
        instanceCount.set(0);
        closedCount.set(0);
    }
}
