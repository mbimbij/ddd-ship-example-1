package com.example.domain.loading;

import com.example.domain.CargoTrackingId;
import com.example.domain.Location;
import com.example.domain.VesselVoyage;
import com.example.domain.common.HandlingEvent;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.time.ZonedDateTime;

@EqualsAndHashCode(callSuper = true)
@Value
public class CargoUnloadedOnVesselEvent extends HandlingEvent {
    ZonedDateTime cargoUnloadingTime;
    Location location;
    VesselVoyage vesselVoyage;

    public CargoUnloadedOnVesselEvent(CargoTrackingId cargoTrackingId, ZonedDateTime cargoUnloadingTime, Location location, VesselVoyage vesselVoyage) {
        super(cargoTrackingId);
        this.cargoUnloadingTime = cargoUnloadingTime;
        this.location = location;
        this.vesselVoyage = vesselVoyage;
    }
}
