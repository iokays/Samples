package com.iokays.cleaner.safety;

import java.lang.ref.Cleaner;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 正确示例：Cleaner 作为安全网
 * 
 * 这是 Cleaner 的合法用途之一：
 * 1. 主要依赖显式的 close() 方法
 * 2. Cleaner 作为后备，防止客户端忘记关闭
 * 3. 清理动作不持有对外部对象的引用
 * 
 * 来自 Effective Java 第8条的示例代码
 */
public class Room implements AutoCloseable {
    private static final Cleaner cleaner = Cleaner.create();
    private static final AtomicInteger instanceCount = new AtomicInteger(0);
    private static final AtomicInteger cleanedCount = new AtomicInteger(0);
    
    // State 类必须不引用 Room 实例！
    // 必须是静态内部类，避免持有对外部类的引用
    private static class State implements Runnable {
        private final int roomId;
        int numJunkPiles; // 需要清理的资源
        
        State(int roomId, int numJunkPiles) {
            this.roomId = roomId;
            this.numJunkPiles = numJunkPiles;
        }
        
        // 由 close() 方法或 Cleaner 调用
        @Override
        public void run() {
            System.out.println("[Room] 清理房间 #" + roomId + 
                             " (垃圾堆数量: " + numJunkPiles + ")");
            numJunkPiles = 0;
            cleanedCount.incrementAndGet();
        }
    }
    
    private final int id;
    private final State state;
    private final Cleaner.Cleanable cleanable;
    
    public Room(int numJunkPiles) {
        this.id = instanceCount.incrementAndGet();
        this.state = new State(id, numJunkPiles);
        this.cleanable = cleaner.register(this, state);
        System.out.println("[Room] 创建房间 #" + id + 
                         " (垃圾堆数量: " + numJunkPiles + ")");
    }
    
    @Override
    public void close() {
        cleanable.clean();
        System.out.println("[Room] 显式关闭房间 #" + id);
    }
    
    public int getId() {
        return id;
    }
    
    public static int getInstanceCount() {
        return instanceCount.get();
    }
    
    public static int getCleanedCount() {
        return cleanedCount.get();
    }
    
    public static void reset() {
        instanceCount.set(0);
        cleanedCount.set(0);
    }
}
