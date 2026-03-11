package com.iokays.cleaner.good;

import java.util.ArrayList;
import java.util.List;

/**
 * 资源管理器：批量管理多个资源
 * 
 * 展示如何正确管理多个需要清理的资源
 */
public class ResourceManager implements AutoCloseable {
    private final List<Resource> resources = new ArrayList<>();
    private boolean closed = false;
    
    /**
     * 创建并添加资源
     */
    public Resource createResource() {
        if (closed) {
            throw new IllegalStateException("管理器已关闭");
        }
        Resource resource = new Resource();
        resources.add(resource);
        return resource;
    }
    
    /**
     * 关闭所有资源（按创建顺序的逆序）
     */
    @Override
    public void close() {
        if (closed) {
            return;
        }
        closed = true;
        
        System.out.println("[ResourceManager] 开始关闭所有资源...");
        
        // 按逆序关闭资源（后创建的先关闭）
        for (int i = resources.size() - 1; i >= 0; i--) {
            try {
                resources.get(i).close();
            } catch (Exception e) {
                // 记录错误但继续关闭其他资源
                System.err.println("[ResourceManager] 关闭资源失败: " + e.getMessage());
            }
        }
        
        System.out.println("[ResourceManager] 所有资源已关闭");
    }
    
    public int getResourceCount() {
        return resources.size();
    }
    
    public boolean isClosed() {
        return closed;
    }
}
