package com.example.application.port.in;

import com.example.domain.CargoTrackingId;
import com.example.domain.Location;
import com.example.domain.VesselVoyage;

import java.time.ZonedDateTime;

@FunctionalInterface
public interface ILoadCargoOnVessel {
    void loadCargo(CargoTrackingId cargoTrackingId, ZonedDateTime timestamp, Location location, VesselVoyage vesselVoyage);
}
