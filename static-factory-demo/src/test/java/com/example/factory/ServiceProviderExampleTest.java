package com.example.factory;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * ServiceProviderExample 测试类
 */
@DisplayName("ServiceProviderExample 测试")
class ServiceProviderExampleTest {
    
    private ServiceProviderExample.MessageService defaultService;
    private ServiceProviderExample.MessageService customService;
    
    @BeforeEach
    void setUp() {
        defaultService = new ServiceProviderExample.DefaultMessageService();
        customService = new ServiceProviderExample.CustomMessageService("Custom Message");
    }
    
    @Test
    @DisplayName("DefaultMessageService 应该返回默认消息")
    void testDefaultMessageService() {
        String message = defaultService.getMessage();
        
        assertEquals("Default Message", message);
    }
    
    @Test
    @DisplayName("CustomMessageService 应该返回自定义消息")
    void testCustomMessageService() {
        String message = customService.getMessage();
        
        assertEquals("Custom Message", message);
    }
    
    @Test
    @DisplayName("getService() 应该返回非空的服务实例")
    void testGetServiceReturnsNonNull() {
        ServiceProviderExample.MessageService service = ServiceProviderExample.getService();
        
        assertNotNull(service);
        assertNotNull(service.getMessage());
    }
    
    @Test
    @DisplayName("多次调用 getService() 可能返回相同或不同的实例")
    void testGetServiceMayReturnSameOrDifferentInstances() {
        ServiceProviderExample.MessageService service1 = ServiceProviderExample.getService();
        ServiceProviderExample.MessageService service2 = ServiceProviderExample.getService();
        
        // 注意：这里只测试不返回 null，具体行为取决于 ServiceLoader 配置
        assertNotNull(service1);
        assertNotNull(service2);
    }
}
