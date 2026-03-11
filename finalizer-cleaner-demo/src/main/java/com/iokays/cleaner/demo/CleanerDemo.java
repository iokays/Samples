package com.iokays.cleaner.demo;

import com.iokays.cleaner.bad.CleanerMisuseDemo;
import com.iokays.cleaner.bad.FinalizerDemo;
import com.iokays.cleaner.good.Resource;
import com.iokays.cleaner.good.ResourceManager;
import com.iokays.cleaner.safety.Room;

/**
 * 综合演示：Finalizer 和 Cleaner 的正确与错误用法
 */
public class CleanerDemo {
    
    public static void main(String[] args) {
        System.out.println("=".repeat(80));
        System.out.println("Finalizer 和 Cleaner 演示");
        System.out.println("=".repeat(80));
        
        // 演示 1: 错误用法 - Finalizer
        demonstrateFinalizer();
        
        // 演示 2: 错误用法 - Cleaner 误用
        demonstrateCleanerMisuse();
        
        // 演示 3: 正确用法 - AutoCloseable
        demonstrateAutoCloseable();
        
        // 演示 4: 正确用法 - Cleaner 作为安全网
        demonstrateSafetyNet();
        
        // 演示 5: 资源管理器
        demonstrateResourceManager();
        
        // 总结
        printSummary();
    }
    
    private static void demonstrateFinalizer() {
        System.out.println("\n" + "=".repeat(80));
        System.out.println("演示 1: Finalizer 的错误用法");
        System.out.println("=".repeat(80));
        
        FinalizerDemo.reset();
        
        // 创建对象但不显式释放
        for (int i = 0; i < 5; i++) {
            new FinalizerDemo();
        }
        
        // 建议 JVM 进行垃圾回收
        System.out.println("\n建议进行垃圾回收...");
        System.gc();
        
        try {
            Thread.sleep(100); // 给 GC 一些时间
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        System.out.println("\n结果:");
        System.out.println("  创建实例数: " + FinalizerDemo.getInstanceCount());
        System.out.println("  终结实例数: " + FinalizerDemo.getFinalizedCount());
        System.out.println("  ⚠️  finalize() 不保证何时执行，甚至可能不执行");
    }
    
    private static void demonstrateCleanerMisuse() {
        System.out.println("\n" + "=".repeat(80));
        System.out.println("演示 2: Cleaner 误用（依赖 Cleaner 而非显式关闭）");
        System.out.println("=".repeat(80));
        
        CleanerMisuseDemo.reset();
        
        // 创建对象但不关闭
        for (int i = 0; i < 3; i++) {
            new CleanerMisuseDemo();
        }
        
        System.out.println("\n建议进行垃圾回收...");
        System.gc();
        
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        System.out.println("\n结果:");
        System.out.println("  创建实例数: " + CleanerMisuseDemo.getInstanceCount());
        System.out.println("  清理实例数: " + CleanerMisuseDemo.getCleanedCount());
        System.out.println("  ⚠️  Cleaner 仍不保证及时执行");
    }
    
    private static void demonstrateAutoCloseable() {
        System.out.println("\n" + "=".repeat(80));
        System.out.println("演示 3: AutoCloseable 正确用法");
        System.out.println("=".repeat(80));
        
        Resource.reset();
        
        // 使用 try-with-resources 自动管理资源
        try (Resource r1 = new Resource();
             Resource r2 = new Resource()) {
            r1.doSomething();
            r2.doSomething();
        } // 自动调用 close()
        
        System.out.println("\n结果:");
        System.out.println("  创建实例数: " + Resource.getInstanceCount());
        System.out.println("  关闭实例数: " + Resource.getClosedCount());
        System.out.println("  ✓ 资源被及时、确定地关闭");
    }
    
    private static void demonstrateSafetyNet() {
        System.out.println("\n" + "=".repeat(80));
        System.out.println("演示 4: Cleaner 作为安全网（合法用法）");
        System.out.println("=".repeat(80));
        
        Room.reset();
        
        // 正确使用：显式关闭
        System.out.println("\n场景 A: 显式关闭（推荐）");
        try (Room room = new Room(7)) {
            System.out.println("使用房间...");
        }
        
        // 安全网：忘记关闭
        System.out.println("\n场景 B: 忘记关闭（安全网生效）");
        Room.reset();
        new Room(99); // 忘记关闭
        
        System.out.println("\n建议进行垃圾回收...");
        System.gc();
        
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        System.out.println("\n结果:");
        System.out.println("  创建实例数: " + Room.getInstanceCount());
        System.out.println("  清理实例数: " + Room.getCleanedCount());
        System.out.println("  ✓ Cleaner 作为安全网，虽然不保证及时，但比完全不清理好");
    }
    
    private static void demonstrateResourceManager() {
        System.out.println("\n" + "=".repeat(80));
        System.out.println("演示 5: 批量资源管理");
        System.out.println("=".repeat(80));
        
        Resource.reset();
        
        try (ResourceManager manager = new ResourceManager()) {
            manager.createResource();
            manager.createResource();
            manager.createResource();
            System.out.println("创建了 " + manager.getResourceCount() + " 个资源");
        }
        
        System.out.println("\n结果:");
        System.out.println("  创建实例数: " + Resource.getInstanceCount());
        System.out.println("  关闭实例数: " + Resource.getClosedCount());
        System.out.println("  ✓ 批量资源被统一管理");
    }
    
    private static void printSummary() {
        System.out.println("\n" + "=".repeat(80));
        System.out.println("总结");
        System.out.println("=".repeat(80));
        
        System.out.println("\n❌ 避免使用 Finalizer:");
        System.out.println("  - 不可预测的执行时间");
        System.out.println("  - 严重性能损失（慢 50 倍）");
        System.out.println("  - 未捕获异常被忽略");
        System.out.println("  - 存在安全漏洞");
        
        System.out.println("\n⚠️  谨慎使用 Cleaner:");
        System.out.println("  - 仍不可预测");
        System.out.println("  - 性能损失（慢 5 倍）");
        System.out.println("  - 仅作为安全网使用");
        
        System.out.println("\n✓ 推荐做法:");
        System.out.println("  - 实现 AutoCloseable 接口");
        System.out.println("  - 使用 try-with-resources");
        System.out.println("  - 显式管理资源生命周期");
        System.out.println("  - Cleaner 仅作为安全网（可选）");
        
        System.out.println("\n" + "=".repeat(80));
    }
}
