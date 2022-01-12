package com.example.application.adapter.out;

import com.example.domain.IStoreEvent;
import com.example.domain.common.DomainEvent;

import java.util.ArrayList;
import java.util.List;

public class InMemoryEventStore implements IStoreEvent {

    private final List<DomainEvent> events = new ArrayList<>();

    @Override
    public void store(DomainEvent domainEvent) {
        events.add(domainEvent);
    }

    public List<DomainEvent> getEvents() {
        return events;
    }

    @Override
    public void handle(DomainEvent domainEvent) {
        store(domainEvent);
    }

    @Override
    public boolean accept(DomainEvent domainEvent) {
        return true;
    }
}
