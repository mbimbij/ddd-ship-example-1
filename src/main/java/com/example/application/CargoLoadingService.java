package com.example.application;

import com.example.application.port.in.ILoadCargoOnVessel;
import com.example.application.port.in.IUnloadCargoFromVessel;
import com.example.domain.Cargo;
import com.example.domain.CargoNotFoundException;
import com.example.domain.CargoRepository;
import com.example.domain.common.DomainEventPublisher;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CargoLoadingService implements ILoadCargoOnVessel, IUnloadCargoFromVessel {
    private final DomainEventPublisher domainEventPublisher;
    private final CargoRepository cargoRepository;

    @Override
    public void loadCargo(CargoLoadingCommand cargoLoadingCommand) {
        Cargo cargo = cargoRepository.findById(cargoLoadingCommand.cargoTrackingId()).orElseThrow(() -> new CargoNotFoundException(cargoLoadingCommand.cargoTrackingId()));
        cargo.load(cargoLoadingCommand.timestamp(), cargoLoadingCommand.location(), cargoLoadingCommand.vesselVoyage());
        domainEventPublisher.publish(cargo.getUncommittedChanges());
    }

    @Override
    public void unloadCargo(CargoLoadingCommand cargoLoadingCommand) {
        Cargo cargo = cargoRepository.findById(cargoLoadingCommand.cargoTrackingId()).orElseThrow(() -> new CargoNotFoundException(cargoLoadingCommand.cargoTrackingId()));
        cargo.unload(cargoLoadingCommand.timestamp(), cargoLoadingCommand.location(), cargoLoadingCommand.vesselVoyage());
        domainEventPublisher.publish(cargo.getUncommittedChanges());
    }
}
