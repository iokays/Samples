package com.iokays.integration.localmessage.event;

import java.time.LocalDateTime;

public abstract class DomainEvent {
    private final int eventVersion;
    private final LocalDateTime occurredOn;

    public DomainEvent() {
        this.eventVersion = 1;
        this.occurredOn = LocalDateTime.now();
    }

    public LocalDateTime getOccurredOn() {
        return this.occurredOn;
    }

}
