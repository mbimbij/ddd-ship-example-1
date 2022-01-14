package com.example.application;

import com.example.application.port.in.ITrackHandlingEvents;
import com.example.domain.CargoTrackingId;
import com.example.domain.DeliveryHistory;
import com.example.domain.common.IStoreEvent;

import java.util.Optional;

public class TrackingService implements ITrackHandlingEvents {
    private final IStoreEvent eventStore;

    public TrackingService(IStoreEvent eventStore) {
        this.eventStore = eventStore;
    }

    @Override
    public Optional<DeliveryHistory> track(CargoTrackingId cargoTrackingId) {
        return Optional.of(eventStore.findById(cargoTrackingId))
                       .filter(handlingEvents -> !handlingEvents.isEmpty())
                       .map(DeliveryHistory::new);
    }
}
