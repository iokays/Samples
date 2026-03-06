package com.iokays.sample.advisor;

import org.springframework.ai.chat.client.advisor.api.CallAdvisor;
import org.springframework.ai.chat.client.advisor.api.CallAdvisorChain;
import org.springframework.ai.chat.client.ChatClientRequest;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.core.Ordered;

/**
 * A custom recursive advisor that evaluates response quality and retries if needed.
 * This advisor checks if the response meets a minimum length threshold and
 * retries with feedback if the quality check fails.
 */
public class QualityCheckAdvisor implements CallAdvisor, Ordered {

    private static final int MAX_RETRIES = 3;
    private static final int MIN_LENGTH_THRESHOLD = 200;
    private final int order;

    public QualityCheckAdvisor() {
        this(Ordered.LOWEST_PRECEDENCE);
    }

    public QualityCheckAdvisor(int order) {
        this.order = order;
    }

    @Override
    public String getName() {
        return "QualityCheckAdvisor";
    }

    @Override
    public int getOrder() {
        return this.order;
    }

    @Override
    public ChatClientResponse adviseCall(ChatClientRequest request, CallAdvisorChain chain) {
        ChatClientResponse response = chain.nextCall(request);
        int attempts = 0;

        while (attempts < MAX_RETRIES && !isHighQuality(response)) {
            String feedback = "Your previous answer was incomplete. "
                + "Please provide a more thorough response (minimum 200 characters).";

            var augmentedPrompt = request
                .prompt()
                .augmentUserMessage(
                    userMessage -> userMessage.mutate()
                        .text(userMessage.getText() + System.lineSeparator() + feedback)
                        .build()
                );

            var augmentedRequest = request
                .mutate()
                .prompt(augmentedPrompt)
                .build();

            response = chain
                .copy(this)
                .nextCall(augmentedRequest);

            attempts++;
        }

        return response;
    }

    /**
     * Checks if the response meets the quality threshold.
     *
     * @param response the chat response to evaluate
     * @return true if response is high quality (exceeds minimum length), false otherwise
     */
    private boolean isHighQuality(ChatClientResponse response) {
        String content = response
            .chatResponse()
            .getResult()
            .getOutput()
            .getText();

        return content != null && content.length() >= MIN_LENGTH_THRESHOLD;
    }
}
