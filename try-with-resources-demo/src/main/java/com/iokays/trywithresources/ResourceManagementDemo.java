package com.iokays.trywithresources;

import com.iokays.trywithresources.custom.CustomResource;

/**
 * try-with-resources 示例演示主程序
 * 
 * 本项目展示了 Java 资源管理的最佳实践：
 * 1. 传统 try-finally 的局限性和问题
 * 2. try-with-resources 的优雅解决方案
 * 3. 异常抑制机制
 * 4. 自定义 AutoCloseable 实现
 */
public class ResourceManagementDemo {
    
    public static void main(String[] args) {
        System.out.println("=== try-with-resources 示例演示 ===\n");
        
        demonstrateBasicUsage();
        System.out.println();
        
        demonstrateMultipleResources();
        System.out.println();
        
        demonstrateExceptionSuppression();
        System.out.println();
        
        System.out.println("=== 演示完成 ===");
    }
    
    /**
     * 演示基本的 try-with-resources 用法
     */
    private static void demonstrateBasicUsage() {
        System.out.println("【示例 1：基本用法】");
        try (CustomResource resource = new CustomResource("Resource-1")) {
            resource.doSomething();
        } catch (Exception e) {
            System.out.println("Caught exception: " + e.getMessage());
        }
    }
    
    /**
     * 演示多个资源的管理
     */
    private static void demonstrateMultipleResources() {
        System.out.println("【示例 2：多个资源管理】");
        try (CustomResource r1 = new CustomResource("Resource-A");
             CustomResource r2 = new CustomResource("Resource-B");
             CustomResource r3 = new CustomResource("Resource-C")) {
            
            r1.doSomething();
            r2.doSomething();
            r3.doSomething();
            
        } catch (Exception e) {
            System.out.println("Caught exception: " + e.getMessage());
        }
    }
    
    /**
     * 演示异常抑制机制
     */
    private static void demonstrateExceptionSuppression() {
        System.out.println("【示例 3：异常抑制机制】");
        try (CustomResource resource = new CustomResource("Exceptional-Resource")) {
            resource.setThrowOnClose(true);  // 关闭时抛出异常
            resource.doSomethingWithException();  // 业务操作抛出异常
        } catch (Exception primary) {
            System.out.println("Primary exception: " + primary.getMessage());
            
            // 获取被抑制的异常
            Throwable[] suppressed = primary.getSuppressed();
            if (suppressed.length > 0) {
                System.out.println("Suppressed exceptions:");
                for (Throwable t : suppressed) {
                    System.out.println("  - " + t.getMessage());
                }
            }
        }
    }
}
