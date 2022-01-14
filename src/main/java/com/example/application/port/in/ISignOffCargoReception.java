package com.example.application.port.in;

import com.example.application.CargoSignoffCommand;

@FunctionalInterface
public interface ISignOffCargoReception {
    void signOff(CargoSignoffCommand cargoSignoffCommand);
}
