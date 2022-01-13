package com.example.domain.loading;

import com.example.domain.CargoTrackingId;
import com.example.domain.Location;
import com.example.domain.VesselVoyage;
import com.example.domain.common.HandlingEvent;
import com.example.domain.common.IdStringGenerator;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.time.ZonedDateTime;

@EqualsAndHashCode(callSuper = true)
@Value
public class CargoLoadedOnVesselEvent extends HandlingEvent {
    CargoTrackingId cargoTrackingId;
    ZonedDateTime cargoLoadingTime;
    Location location;
    VesselVoyage vesselVoyage;

    public CargoLoadedOnVesselEvent(CargoTrackingId cargoTrackingId, ZonedDateTime cargoLoadingTime, Location location, VesselVoyage vesselVoyage) {
        super(IdStringGenerator.nextIdString());
        this.cargoTrackingId = cargoTrackingId;
        this.cargoLoadingTime = cargoLoadingTime;
        this.location = location;
        this.vesselVoyage = vesselVoyage;
    }
}
