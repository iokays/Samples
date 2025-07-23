package com.iokays.sample.config;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

@Component
public class HibernateDomainEventPublisher implements ApplicationEventPublisherAware {

    private static ApplicationEventPublisher domainEventPublisher;

    protected static void publish(Object event) {
        if (domainEventPublisher != null) {
            domainEventPublisher.publishEvent(event);
        }
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        domainEventPublisher = applicationEventPublisher;
    }

}
