package com.iokays.sample.example;

import com.iokays.sample.model.BookSummary;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.StructuredOutputValidationAdvisor;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;

/**
 * Example demonstrating StructuredOutputValidationAdvisor usage.
 * This example shows how to use the advisor to ensure LLM output
 * matches a specific Java record schema.
 */
public class StructuredOutputExample {

    /**
     * Creates a ChatClient configured with StructuredOutputValidationAdvisor.
     *
     * @param chatModel the OpenAI chat model
     * @return configured ChatClient
     */
    public static ChatClient createStructuredOutputChatClient(OpenAiChatModel chatModel) {
        var validationAdvisor = StructuredOutputValidationAdvisor
            .builder()
            .outputType(BookSummary.class)
            .maxRepeatAttempts(5)
            .build();

        return ChatClient
            .builder(chatModel)
            .defaultOptions(OpenAiChatOptions.builder().temperature(0.7).build())
            .defaultAdvisors(validationAdvisor)
            .build();
    }

    /**
     * Main method demonstrating the structured output validation advisor.
     */
    public static void main(String[] args) {
        System.out.println("StructuredOutputValidationAdvisor Example Configuration:");
        System.out.println("===================================================");
        System.out.println("Target Type: BookSummary");
        System.out.println("Fields:");
        System.out.println("  - title: String");
        System.out.println("  - author: String");
        System.out.println("  - themes: List<String>");
        System.out.println("Max Retry Attempts: 5");
        System.out.println();
        System.out.println("To use with a real API key, configure the OpenAI chat model");
        System.out.println("and call:");
        System.out.println("  BookSummary result = chatClient.prompt()");
        System.out.println("    .user(\"Summarize '1984' by George Orwell with its main themes\")");
        System.out.println("    .call()");
        System.out.println("    .entity(BookSummary.class);");
        System.out.println();
        System.out.println("Expected Output Structure:");
        System.out.println("  BookSummary[");
        System.out.println("    title=\"1984\",");
        System.out.println("    author=\"George Orwell\",");
        System.out.println("    themes=[\"Surveillance\", \"Totalitarianism\", \"Reality Control\"]");
        System.out.println("  ]");
    }
}
