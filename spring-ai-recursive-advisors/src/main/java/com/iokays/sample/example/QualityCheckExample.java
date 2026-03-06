package com.iokays.sample.example;

import com.iokays.sample.advisor.QualityCheckAdvisor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;

/**
 * Example demonstrating custom QualityCheckAdvisor usage.
 * This example shows how to use a custom recursive advisor that
 * evaluates response quality and retries with feedback if needed.
 */
public class QualityCheckExample {

    /**
     * Creates a ChatClient configured with QualityCheckAdvisor.
     *
     * @param chatModel: OpenAI chat model
     * @return configured ChatClient
     */
    public static ChatClient createQualityCheckChatClient(OpenAiChatModel chatModel) {
        return ChatClient
            .builder(chatModel)
            .defaultOptions(OpenAiChatOptions.builder().temperature(0.7).build())
            .defaultAdvisors(new QualityCheckAdvisor())
            .build();
    }

    /**
     * Main method demonstrating quality check advisor.
     */
    public static void main(String[] args) {
        System.out.println("QualityCheckAdvisor Example Configuration:");
        System.out.println("======================================");
        System.out.println("Advisor: QualityCheckAdvisor");
        System.out.println("Max Retries: 3");
        System.out.println("Minimum Length Threshold: 200 characters");
        System.out.println();
        System.out.println("Behavior:");
        System.out.println("  - If response < 200 chars: Retry with feedback");
        System.out.println("  - If response >= 200 chars: Return result");
        System.out.println("  - After 3 retries: Return best available response");
        System.out.println();
        System.out.println("To use with a real API key, configure as OpenAI chat model");
        System.out.println("and call:");
        System.out.println("  String result = chatClient.prompt()");
        System.out.println("    .user(\"Explain the SOLID principles in detail.\")");
        System.out.println("    .call()");
        System.out.println("    .content();");
        System.out.println();
        System.out.println("The advisor will automatically:");
        System.out.println("  1. Call the LLM with the original prompt");
        System.out.println("  2. Check if response meets quality threshold");
        System.out.println("  3. If not, add feedback and retry");
        System.out.println("  4. Repeat up to 3 times until quality check passes");
    }
}
