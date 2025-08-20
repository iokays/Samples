package com.iokays.sample.config;

import com.iokays.sample.core.adapter.mcp.FileMcpService;
import lombok.AllArgsConstructor;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@AllArgsConstructor
public class McpToolsConfig {

    private final FileMcpService fileMcpService;

    @Bean
    public ToolCallbackProvider weatherTools() {
        return MethodToolCallbackProvider.builder().toolObjects(fileMcpService).build();
    }

}
