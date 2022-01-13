package com.example.application;

import com.example.application.port.in.ILoadCargoOnVessel;
import com.example.application.port.in.IUnloadCargoFromVessel;
import com.example.domain.Cargo;
import com.example.domain.CargoNotFoundException;
import com.example.domain.CargoRepository;
import com.example.domain.CargoTrackingId;
import com.example.domain.Location;
import com.example.domain.VesselVoyage;
import com.example.domain.common.DomainEventPublisher;
import lombok.RequiredArgsConstructor;

import java.time.ZonedDateTime;

@RequiredArgsConstructor
public class CargoLoadingService implements ILoadCargoOnVessel, IUnloadCargoFromVessel {
    private final DomainEventPublisher domainEventPublisher;
    private final CargoRepository cargoRepository;

    @Override
    public void loadCargo(CargoTrackingId cargoTrackingId, ZonedDateTime timestamp, Location location, VesselVoyage vesselVoyage) {
        Cargo cargo = cargoRepository.findById(cargoTrackingId).orElseThrow(() -> new CargoNotFoundException(cargoTrackingId));
        cargo.load(timestamp, location, vesselVoyage);
        domainEventPublisher.publish(cargo.getUncommittedChanges());
    }

    @Override
    public void unloadCargo(CargoTrackingId cargoTrackingId, ZonedDateTime timestamp, Location location, VesselVoyage vesselVoyage) {
        Cargo cargo = cargoRepository.findById(cargoTrackingId).orElseThrow(() -> new CargoNotFoundException(cargoTrackingId));
        cargo.unload(timestamp, location, vesselVoyage);
        domainEventPublisher.publish(cargo.getUncommittedChanges());
    }
}
