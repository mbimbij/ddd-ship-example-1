package com.example.application;

import com.example.application.port.in.ISignOffCargoReception;
import com.example.domain.Cargo;
import com.example.domain.CargoNotFoundException;
import com.example.domain.CargoRepository;
import com.example.domain.CargoTrackingId;
import com.example.domain.Customer;
import com.example.domain.Location;
import com.example.domain.common.DomainEventPublisher;
import lombok.RequiredArgsConstructor;

import java.time.ZonedDateTime;

@RequiredArgsConstructor
public class CargoReceptionSignOffService implements ISignOffCargoReception {
    private final DomainEventPublisher eventPublisher;
    private final CargoRepository cargoRepository;

    @Override
    public void signOff(CargoSignoffCommand cargoSignoffCommand) {
        CargoTrackingId cargoTrackingId = cargoSignoffCommand.cargoTrackingId();
        Customer recipient = cargoSignoffCommand.recipient();
        Location signoffLocation = cargoSignoffCommand.location();
        ZonedDateTime receptionTimestamp = cargoSignoffCommand.receptionTimestamp();

        Cargo cargo = cargoRepository.findById(cargoTrackingId).orElseThrow(() -> new CargoNotFoundException(cargoTrackingId));
        cargo.signOff(recipient, signoffLocation, receptionTimestamp);
        eventPublisher.publish(cargo.getUncommittedChanges());
    }
}
