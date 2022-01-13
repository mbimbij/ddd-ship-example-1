package com.example.application.port.in;

import com.example.domain.CargoTrackingId;
import com.example.domain.Location;
import com.example.domain.VesselVoyage;

import java.time.ZonedDateTime;

public interface IUnloadCargoFromVessel {
    void unloadCargo(CargoTrackingId cargoTrackingId, ZonedDateTime cargoUnloadingTime, Location arrival, VesselVoyage vesselVoyage);
}
