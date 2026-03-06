package com.iokays.sample.example;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;

/**
 * Example demonstrating ToolCallAdvisor usage.
 * This example shows how to use ToolCallAdvisor to handle multiple tool calls
 * in sequence for currency conversion.
 */
public class ToolCallExample {

    /**
     * Creates a ChatClient configured with ToolCallAdvisor.
     *
     * @param chatModel OpenAI chat model
     * @return configured ChatClient
     */
    public static ChatClient createToolCallChatClient(OpenAiChatModel chatModel) {
        return ChatClient
            .builder(chatModel)
            .defaultOptions(OpenAiChatOptions.builder().temperature(0.7).build())
            .build();
    }

    /**
     * Main method demonstrating to tool call advisor.
     */
    public static void main(String[] args) {
        System.out.println("ToolCallAdvisor Example Configuration:");
        System.out.println("====================================");
        System.out.println();
        System.out.println("To use with a real API key, configure as OpenAI chat model");
        System.out.println("and call:");
        System.out.println("  String answer = chatClient.prompt()");
        System.out.println("    .user(\"Convert 500 USD to EUR and then to GBP\")");
        System.out.println("    .call()");
        System.out.println("    .content();");
    }
}
