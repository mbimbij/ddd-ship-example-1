package com.example.utils;

import com.example.adapter.out.InMemoryEventStore;
import com.example.domain.common.DomainEventPublisher;

public class TestUtils {
    public static Pair<InMemoryEventStore, DomainEventPublisher> createEventStoreAndPublisher() {
        DomainEventPublisher domainEventPublisher = new DomainEventPublisher();
        InMemoryEventStore inMemoryEventStore = new InMemoryEventStore();
        domainEventPublisher.subscribe(inMemoryEventStore);
        return new Pair<>(inMemoryEventStore, domainEventPublisher);
    }
}
