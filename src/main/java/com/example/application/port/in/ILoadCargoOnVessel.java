package com.example.application.port.in;

import com.example.application.CargoLoadingCommand;

@FunctionalInterface
public interface ILoadCargoOnVessel {
    void loadCargo(CargoLoadingCommand cargoLoadingCommand);
}
