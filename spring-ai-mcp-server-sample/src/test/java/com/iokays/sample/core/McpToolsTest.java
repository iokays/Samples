package com.iokays.sample.core;

import io.modelcontextprotocol.client.McpAsyncClient;
import io.modelcontextprotocol.client.McpClient;
import io.modelcontextprotocol.client.transport.HttpClientSseClientTransport;
import io.modelcontextprotocol.spec.McpSchema;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;

class McpToolsTest { // Adjusted class name

    @Test
    void test() {
        String mcpServerUrl = "http://localhost:8080/mcp/messages";

        HttpClientSseClientTransport transport = HttpClientSseClientTransport.builder(mcpServerUrl).build();
        McpAsyncClient client = McpClient.async(transport).build();

        final List<McpSchema.Tool> tools = Objects.requireNonNull(client.initialize().then(client.listTools()).block()).tools()
                .stream()
                .filter(tool -> "getWeather".equals(tool.name())).toList();

        Assertions.assertEquals(1, tools.size());
        Assertions.assertEquals("getWeather", tools.getFirst().name());
        Assertions.assertEquals("获取指定城市的天气信息", tools.getFirst().description());
    }
}
