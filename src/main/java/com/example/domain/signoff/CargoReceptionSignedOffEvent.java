package com.example.domain.signoff;

import com.example.domain.CargoTrackingId;
import com.example.domain.Customer;
import com.example.domain.common.HandlingEvent;
import com.example.domain.common.IdStringGenerator;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.time.ZonedDateTime;

@EqualsAndHashCode(callSuper = true)
@Value
public class CargoReceptionSignedOffEvent extends HandlingEvent {
    Customer recipient;
    CargoTrackingId cargoTrackingId;
    ZonedDateTime receptionTimestamp;

    public CargoReceptionSignedOffEvent(Customer recipient, CargoTrackingId cargoTrackingId, ZonedDateTime receptionTimestamp) {
        super(IdStringGenerator.nextIdString());
        this.recipient = recipient;
        this.cargoTrackingId = cargoTrackingId;
        this.receptionTimestamp = receptionTimestamp;
    }
}
