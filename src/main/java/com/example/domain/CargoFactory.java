package com.example.domain;

import java.util.UUID;

public class CargoFactory {
    public static Cargo newCargo(Location departure, Location arrival) {
        return new Cargo(new TrackingId(UUID.randomUUID().toString()), departure, arrival);
    }
}
