package com.iokays.integration.localmessage.publisher;

import com.iokays.integration.localmessage.event.DomainEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class DomainPublisher implements ApplicationEventPublisherAware {

    private static ApplicationEventPublisher publisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        publisher = Objects.requireNonNull(applicationEventPublisher);
    }

    public static void publish(DomainEvent event) {
        publisher.publishEvent(Objects.requireNonNull(event));
    }
}
