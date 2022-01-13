package com.example.application.port.in;

import com.example.application.CargoBookingCommand;
import com.example.domain.CargoTrackingId;

@FunctionalInterface
public interface IBookCargo {
    CargoTrackingId book(CargoBookingCommand cargoBookingCommand);
}
