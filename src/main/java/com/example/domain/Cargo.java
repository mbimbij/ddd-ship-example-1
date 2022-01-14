package com.example.domain;

import com.example.domain.booking.CargoBookedEvent;
import com.example.domain.common.HandlingEvent;
import com.example.domain.loading.CargoLoadedOnVesselEvent;
import com.example.domain.loading.CargoUnloadedFromVesselEvent;
import com.example.domain.signoff.CargoNotAtDestinationException;
import com.example.domain.signoff.CargoReceptionSignedOffEvent;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Builder
public class Cargo {
    private final CargoTrackingId cargoTrackingId;
    private final Customer payer;
    private final Customer recipient;
    private final DeliverySpecification deliverySpecification;

    private final List<HandlingEvent> uncommittedChanges = new ArrayList<>();

    public Cargo(CargoBookedEvent cargoBookedEvent) {
        cargoTrackingId = cargoBookedEvent.getCargoTrackingId();
        payer = cargoBookedEvent.getPayer();
        recipient = cargoBookedEvent.getRecipient();
        deliverySpecification = new DeliverySpecification(cargoBookedEvent.getDeparture(), cargoBookedEvent.getArrival());
    }

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

    public void load(ZonedDateTime timestamp, Location location, VesselVoyage vesselVoyage) {
        uncommittedChanges.add(new CargoLoadedOnVesselEvent(cargoTrackingId, timestamp, location, vesselVoyage));
    }

    public void unload(ZonedDateTime timestamp, Location location, VesselVoyage vesselVoyage) {
        uncommittedChanges.add(new CargoUnloadedFromVesselEvent(cargoTrackingId, timestamp, location, vesselVoyage));
    }

    public void signOff(Customer recipient, Location location, ZonedDateTime receptionTimestamp) {
        if(!isArrivedAtDestination(location)){
            throw new CargoNotAtDestinationException();
        }
        uncommittedChanges.add(new CargoReceptionSignedOffEvent(recipient, cargoTrackingId, receptionTimestamp));
    }

    private boolean isArrivedAtDestination(Location location) {
        return Objects.equals(location, deliverySpecification.getArrival());
    }
}
