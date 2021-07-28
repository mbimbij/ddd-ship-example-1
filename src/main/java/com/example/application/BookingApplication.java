package com.example.application;

import com.example.domain.Cargo;
import com.example.domain.CargoFactory;
import com.example.domain.CargoRepository;
import com.example.domain.Location;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BookingApplication {

    private final CargoRepository cargoRepository;

    public Cargo bookNewCargo(Location departure, Location arrival) {
        Cargo cargo = CargoFactory.newCargo(departure, arrival);
        cargoRepository.save(cargo);
        return cargo;
    }
}
