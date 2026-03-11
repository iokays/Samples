package com.iokays.trywithresources.custom;

/**
 * 自定义资源类，实现 AutoCloseable 接口
 * 演示如何创建可自动管理的资源
 */
public class CustomResource implements AutoCloseable {
    
    private final String name;
    private boolean closed = false;
    private boolean throwOnClose = false;
    
    public CustomResource(String name) {
        this.name = name;
        System.out.println("[" + name + "] Resource opened");
    }
    
    /**
     * 设置关闭时是否抛出异常（用于测试异常抑制）
     */
    public void setThrowOnClose(boolean throwOnClose) {
        this.throwOnClose = throwOnClose;
    }
    
    /**
     * 执行业务操作
     */
    public void doSomething() {
        if (closed) {
            throw new IllegalStateException("Resource is already closed");
        }
        System.out.println("[" + name + "] Doing something...");
    }
    
    /**
     * 执行业务操作并可能抛出异常
     */
    public void doSomethingWithException() throws Exception {
        if (closed) {
            throw new IllegalStateException("Resource is already closed");
        }
        System.out.println("[" + name + "] Doing something with exception...");
        throw new Exception("Business logic exception from " + name);
    }
    
    /**
     * 实现 AutoCloseable 接口的 close 方法
     * 该方法会在 try-with-resources 语句中自动调用
     */
    @Override
    public void close() throws Exception {
        if (!closed) {
            closed = true;
            System.out.println("[" + name + "] Resource closed");
            
            if (throwOnClose) {
                throw new Exception("Close exception from " + name);
            }
        }
    }
    
    public boolean isClosed() {
        return closed;
    }
    
    public String getName() {
        return name;
    }
}
