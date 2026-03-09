package com.example.factory;

import java.util.Map;
import java.util.ServiceLoader;

/**
 * 服务提供者框架示例
 * 
 * 演示静态工厂方法的优势 5：
 * 在编写包含该方法的类时，返回的对象的类不需要存在
 * 
 * 这是服务提供者框架的基础，如 JDBC
 */
public class ServiceProviderExample {
    
    /**
     * 服务接口
     */
    public interface MessageService {
        String getMessage();
    }
    
    /**
     * 默认服务实现
     */
    public static class DefaultMessageService implements MessageService {
        @Override
        public String getMessage() {
            return "Default Message";
        }
    }
    
    /**
     * 自定义服务实现
     */
    public static class CustomMessageService implements MessageService {
        private final String message;
        
        public CustomMessageService(String message) {
            this.message = message;
        }
        
        @Override
        public String getMessage() {
            return message;
        }
    }
    
    /**
     * 服务提供者
     */
    public interface MessageServiceProvider {
        MessageService createService();
    }
    
    /**
     * 服务访问 API（静态工厂方法）
     * 
     * 这个方法可以在编译时不知道具体实现类的情况下工作
     * 实现类可以在运行时通过配置或服务发现机制加载
     * 
     * @return 服务实例
     */
    public static MessageService getService() {
        // 1. 尝试从 ServiceLoader 加载服务
        ServiceLoader<MessageServiceProvider> loader = 
            ServiceLoader.load(MessageServiceProvider.class);
        
        for (MessageServiceProvider provider : loader) {
            return provider.createService();
        }
        
        // 2. 如果没有找到，返回默认实现
        return new DefaultMessageService();
    }
    
    /**
     * 演示服务提供者框架
     */
    public static void demonstrateServiceProvider() {
        System.out.println("=== 服务提供者框架 ===\n");
        
        // 获取服务（可能来自 ServiceLoader 或默认实现）
        MessageService service = getService();
        
        System.out.println("1. 服务获取");
        System.out.println("通过静态工厂方法获取服务实例");
        System.out.println("服务实现: " + service.getClass().getSimpleName());
        System.out.println("服务消息: " + service.getMessage() + "\n");
        
        System.out.println("2. 优势分析");
        System.out.println("- 客户端与具体实现解耦");
        System.out.println("- 可以通过配置文件切换实现");
        System.out.println("- 符合依赖反转原则");
        System.out.println("- 便于实现插件化架构");
        System.out.println("- 例如：JDBC 的 DriverManager.getConnection()\n");
        
        System.out.println("3. JDBC 示例");
        System.out.println("Connection conn = DriverManager.getConnection(url);");
        System.out.println("编译时无需知道具体的数据库驱动实现");
        System.out.println("运行时根据 URL 加载对应的驱动");
    }
}
