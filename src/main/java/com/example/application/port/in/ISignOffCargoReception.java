package com.example.application.port.in;

import com.example.application.CargoSignoffCommand;

public interface ISignOffCargoReception {
    void signOff(CargoSignoffCommand cargoSignoffCommand);
}
