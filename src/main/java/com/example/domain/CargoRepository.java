package com.example.domain;

import com.example.domain.booking.CargoBookedEvent;
import com.example.domain.common.HandlingEvent;
import com.example.domain.common.IStoreEvent;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class CargoRepository {
    private final IStoreEvent iStoreEvent;

    public Optional<Cargo> findById(CargoTrackingId cargoTrackingId) {
        List<HandlingEvent> events = iStoreEvent.findById(cargoTrackingId);
        if (events.isEmpty()) {
            return Optional.empty();
        } else if (!(events.get(0) instanceof CargoBookedEvent)) {
            throw new IllegalStateException("first handling event MUST be of type \"CargoBookedEvent\", but was: " + events.get(0).getClass());
        }

        CargoBookedEvent cargoBookedEvent = (CargoBookedEvent) events.get(0);
        Cargo cargo = new Cargo(cargoBookedEvent);

        return Optional.of(cargo);
    }
}
