package com.example.domain.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DomainEventPublisher {
    private final List<IHandleDomainEvents> eventHandlers = new ArrayList<>();

    public void publish(HandlingEvent handlingEvent) {
        eventHandlers.stream()
                     .filter(IHandleDomainEvents -> IHandleDomainEvents.accept(handlingEvent))
                     .forEach(IHandleDomainEvents -> IHandleDomainEvents.handle(handlingEvent));
    }

    public void publish(Collection<HandlingEvent> handlingEvents) {
        handlingEvents.forEach(handlingEvent -> eventHandlers.stream()
                                                             .filter(IHandleDomainEvents -> IHandleDomainEvents.accept(handlingEvent))
                                                             .forEach(IHandleDomainEvents -> IHandleDomainEvents.handle(handlingEvent)));
        ;
    }

    public void subscribe(IHandleDomainEvents eventHandler) {
        eventHandlers.add(eventHandler);
    }
}
