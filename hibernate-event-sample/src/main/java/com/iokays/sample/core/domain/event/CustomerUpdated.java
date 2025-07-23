package com.iokays.sample.core.domain.event;

import com.iokays.sample.core.common.DomainEvent;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CustomerUpdated(String id, String name, LocalDateTime dateTime) implements DomainEvent<CustomerUpdated> {
}
