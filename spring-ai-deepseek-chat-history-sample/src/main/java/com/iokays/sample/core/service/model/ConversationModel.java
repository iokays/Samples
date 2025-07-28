package com.iokays.sample.core.service.model;

import lombok.Builder;

@Builder
public record ConversationModel(String conversationId, String message) {
}

