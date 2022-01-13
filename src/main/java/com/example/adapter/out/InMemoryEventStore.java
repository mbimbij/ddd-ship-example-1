package com.example.adapter.out;

import com.example.domain.common.HandlingEvent;
import com.example.domain.common.IStoreEvent;
import com.example.domain.common.Id;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class InMemoryEventStore implements IStoreEvent {

    private final List<HandlingEvent> events = new ArrayList<>();

    @Override
    public void store(HandlingEvent handlingEvent) {
        events.add(handlingEvent);
    }

    @Override
    public List<HandlingEvent> findById(Id<String> id) {
        return events.stream()
                     .filter(handlingEvent -> handlingEvent.getId().sameAs(id))
                     .collect(Collectors.toList());
    }

    public List<HandlingEvent> getEvents() {
        return events;
    }

    @Override
    public void handle(HandlingEvent handlingEvent) {
        store(handlingEvent);
    }

    @Override
    public boolean accept(HandlingEvent handlingEvent) {
        return true;
    }
}
