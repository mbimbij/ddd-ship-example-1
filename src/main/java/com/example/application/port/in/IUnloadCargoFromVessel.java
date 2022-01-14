package com.example.application.port.in;

import com.example.application.CargoLoadingCommand;

@FunctionalInterface
public interface IUnloadCargoFromVessel {
    void unloadCargo(CargoLoadingCommand cargoLoadingCommand);
}
