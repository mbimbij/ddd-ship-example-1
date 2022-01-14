package com.example.domain;

import com.example.domain.common.HandlingEvent;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode
@RequiredArgsConstructor
public class DeliveryHistory {
    private final List<HandlingEvent> handlingEvents;

    public DeliveryHistory() {
        handlingEvents = new ArrayList<>();
    }

    public List<HandlingEvent> getHandlingEvents() {
        return List.copyOf(handlingEvents);
    }
}
