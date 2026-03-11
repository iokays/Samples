package com.iokays.cleaner.bad;

import java.lang.ref.Cleaner;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 错误示例：Cleaner 误用
 * 
 * 虽然 Cleaner 比 Finalizer 稍好，但仍存在类似问题：
 * 1. 执行时间仍然不可预测
 * 2. 性能损失（比正常关闭慢 5 倍）
 * 3. 不保证一定执行
 * 
 * 这个示例展示了常见的 Cleaner 误用模式
 * 
 * @deprecated 不应该依赖 Cleaner 进行资源清理
 */
@Deprecated
public class CleanerMisuseDemo implements AutoCloseable {
    private static final Cleaner cleaner = Cleaner.create();
    private static final AtomicInteger instanceCount = new AtomicInteger(0);
    private static final AtomicInteger cleanedCount = new AtomicInteger(0);
    
    private final int id;
    private final Cleaner.Cleanable cleanable;
    
    public CleanerMisuseDemo() {
        this.id = instanceCount.incrementAndGet();
        System.out.println("[CleanerMisuseDemo] 创建实例 #" + id);
        
        // 注册清理动作
        this.cleanable = cleaner.register(this, new CleaningAction(id));
    }
    
    @Override
    public void close() {
        System.out.println("[CleanerMisuseDemo] 显式关闭实例 #" + id);
        cleanable.clean();
    }
    
    /**
     * 清理动作
     * 注意：这个类不应该引用外部的 CleanerMisuseDemo 实例
     * 否则会阻止垃圾回收
     */
    private static class CleaningAction implements Runnable {
        private final int id;
        private boolean cleaned = false;
        
        CleaningAction(int id) {
            this.id = id;
        }
        
        @Override
        public void run() {
            if (cleaned) {
                return; // 防止重复清理
            }
            cleaned = true;
            int cleaned = cleanedCount.incrementAndGet();
            System.out.println("[CleanerMisuseDemo] 清理实例 #" + id + 
                             " (已清理: " + cleaned + "/" + instanceCount.get() + ")");
        }
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
