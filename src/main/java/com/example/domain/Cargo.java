package com.example.domain;

import com.example.domain.booking.CargoBookedEvent;
import com.example.domain.common.DomainEvent;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Cargo {
    private final CargoTrackingId cargoTrackingId;
    private final Customer payer;
    private final Customer recipient;
    private final DeliverySpecification deliverySpecification;

    private final List<DomainEvent> uncommittedChanges = new ArrayList<>();

    public Cargo(CargoTrackingId cargoTrackingId, Customer payer, Customer recipient, DeliverySpecification deliverySpecification) {
        this.cargoTrackingId = cargoTrackingId;
        this.payer = payer;
        this.recipient = recipient;
        this.deliverySpecification = deliverySpecification;

        uncommittedChanges.add(new CargoBookedEvent(cargoTrackingId,
                                                    payer,
                                                    recipient,
                                                    deliverySpecification.getDeparture(),
                                                    deliverySpecification.getArrival()));
    }
}
