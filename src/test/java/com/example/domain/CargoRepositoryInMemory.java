package com.example.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CargoRepositoryInMemory implements CargoRepository {
    private final Map<TrackingId, Cargo> datastore = new HashMap<>();

    @Override
    public void save(Cargo cargo) {
        datastore.put(cargo.getTrackingId(), cargo);
    }

    @Override
    public Optional<Cargo> findByTrackingId(TrackingId trackingId) {
        return Optional.ofNullable(datastore.get(trackingId));
    }
}
