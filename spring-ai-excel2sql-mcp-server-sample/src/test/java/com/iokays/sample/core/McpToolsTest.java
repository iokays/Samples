package com.iokays.sample.core;

import io.modelcontextprotocol.client.McpAsyncClient;
import io.modelcontextprotocol.client.McpClient;
import io.modelcontextprotocol.client.transport.HttpClientSseClientTransport;
import io.modelcontextprotocol.spec.McpSchema;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;

@Slf4j
class McpToolsTest { // Adjusted class name

    @Test
    void test() {
        String mcpServerUrl = "http://localhost:8080/mcp/messages";

        HttpClientSseClientTransport transport = HttpClientSseClientTransport.builder(mcpServerUrl).build();
        McpAsyncClient client = McpClient.async(transport).build();

        final List<McpSchema.Tool> tools = Objects.requireNonNull(client.initialize().then(client.listTools()).block()).tools();
        Assertions.assertEquals(2, tools.size());
    }
}
