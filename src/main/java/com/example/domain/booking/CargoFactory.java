package com.example.domain.booking;

import com.example.domain.Cargo;
import com.example.domain.CargoTrackingId;
import com.example.domain.Customer;
import com.example.domain.DeliverySpecification;
import com.example.domain.Location;
import com.example.domain.common.IdStringGenerator;

public class CargoFactory {
    public Cargo createCargo(Customer payer,
                             Customer recipient,
                             Location departure,
                             Location arrival) {
        CargoTrackingId cargoTrackingId = new CargoTrackingId(IdStringGenerator.nextString());
        DeliverySpecification deliverySpecification = new DeliverySpecification(departure, arrival);
        return new Cargo(cargoTrackingId, payer, recipient, deliverySpecification);
    }
}
