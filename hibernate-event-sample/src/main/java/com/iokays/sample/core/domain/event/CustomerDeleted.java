package com.iokays.sample.core.domain.event;

import com.iokays.sample.core.common.DomainEvent;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CustomerDeleted(String id, LocalDateTime dateTime) implements DomainEvent<CustomerDeleted> {
}
