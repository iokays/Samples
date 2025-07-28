package com.iokays.sample.config;

import com.iokays.sample.core.adapter.mcp.WeatherMcpService;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class McpToolsConfig {
    @Bean
    public ToolCallbackProvider weatherTools(WeatherMcpService weatherMcpService) {
        return MethodToolCallbackProvider.builder().toolObjects(weatherMcpService).build();
    }

}
