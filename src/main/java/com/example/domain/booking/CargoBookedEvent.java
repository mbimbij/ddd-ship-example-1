package com.example.domain.booking;

import com.example.domain.CargoTrackingId;
import com.example.domain.Customer;
import com.example.domain.Location;
import com.example.domain.common.HandlingEvent;
import com.example.domain.common.IdStringGenerator;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class CargoBookedEvent extends HandlingEvent {
    CargoTrackingId cargoTrackingId;
    Customer payer;
    Customer recipient;
    Location departure;
    Location arrival;

    public CargoBookedEvent(CargoTrackingId cargoTrackingId, Customer payer, Customer recipient, Location departure, Location arrival) {
        super(IdStringGenerator.nextIdString());
        this.cargoTrackingId = cargoTrackingId;
        this.payer = payer;
        this.recipient = recipient;
        this.departure = departure;
        this.arrival = arrival;
    }
}
