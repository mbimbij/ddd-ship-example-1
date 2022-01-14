package com.example.application;

import com.example.domain.CargoTrackingId;
import com.example.domain.Location;
import com.example.domain.VesselVoyage;

import java.time.ZonedDateTime;

public record CargoLoadingCommand(
        CargoTrackingId cargoTrackingId,
        ZonedDateTime timestamp,
        Location location,
        VesselVoyage vesselVoyage
) {
}