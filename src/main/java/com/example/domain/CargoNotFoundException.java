package com.example.domain;

public class CargoNotFoundException extends RuntimeException {
    public CargoNotFoundException(CargoTrackingId cargoTrackingId) {
        super(cargoTrackingId.getValue());
    }
}
