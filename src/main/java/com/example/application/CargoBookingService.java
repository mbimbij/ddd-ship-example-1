package com.example.application;

import com.example.application.port.in.IBookCargo;
import com.example.domain.Cargo;
import com.example.domain.CargoTrackingId;
import com.example.domain.booking.CargoFactory;
import com.example.domain.common.DomainEventPublisher;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CargoBookingService implements IBookCargo {

    private final DomainEventPublisher eventPublisher;
    private final CargoFactory cargoFactory;

    @Override
    public CargoTrackingId book(CargoBookingCommand cargoBookingCommand) {
        Cargo cargo = cargoFactory.createCargo(cargoBookingCommand.getPayer(),
                                               cargoBookingCommand.getRecipient(),
                                               cargoBookingCommand.getDeparture(),
                                               cargoBookingCommand.getArrival());
        cargo.getUncommittedChanges().forEach(eventPublisher::publish);
        return cargo.getCargoTrackingId();
    }
}
