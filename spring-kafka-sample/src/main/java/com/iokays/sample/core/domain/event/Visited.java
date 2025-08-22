package com.iokays.sample.core.domain.event;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record Visited(String customerId, LocalDateTime time) {
}
