package com.example.domain;

import java.util.Optional;

public interface CargoRepository {
    void save(Cargo cargo);

    Optional<Cargo> findByTrackingId(TrackingId trackingId);
}
