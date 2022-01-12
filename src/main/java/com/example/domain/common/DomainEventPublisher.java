package com.example.domain.common;

import java.util.ArrayList;
import java.util.List;

public class DomainEventPublisher {
    private final List<IHandleDomainEvents> eventHandlers = new ArrayList<>();

    public void publish(DomainEvent domainEvent) {
        eventHandlers.stream()
                     .filter(IHandleDomainEvents -> IHandleDomainEvents.accept(domainEvent))
                     .forEach(IHandleDomainEvents -> IHandleDomainEvents.handle(domainEvent));
    }

    public void subscribe(IHandleDomainEvents eventHandler){
        eventHandlers.add(eventHandler);
    }
}
