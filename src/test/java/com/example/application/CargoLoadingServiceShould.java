package com.example.application;

import com.example.adapter.out.InMemoryEventStore;
import com.example.domain.CargoRepository;
import com.example.domain.CargoTrackingId;
import com.example.domain.Customer;
import com.example.domain.Location;
import com.example.domain.VesselVoyage;
import com.example.domain.VesselVoyage.Type;
import com.example.domain.booking.CargoBookedEvent;
import com.example.domain.common.DomainEventPublisher;
import com.example.domain.common.IdStringGenerator;
import com.example.domain.loading.CargoLoadedOnVesselEvent;
import com.example.utils.Pair;
import com.example.utils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class CargoLoadingServiceShould {

    @BeforeEach
    void setUp() {
        String id = "id";
        IdStringGenerator.setNextStringGenerator(() -> id);
    }

    @Test
    void sendAnEvent_whenLoadingCargo() {
        // GIVEN
        CargoTrackingId cargoTrackingId = new CargoTrackingId("id");
        Customer payer = new Customer("payer");
        Customer recipient = new Customer("recipient");
        Location departure = new Location("departure");
        Location arrival = new Location("arrival");
        Pair<InMemoryEventStore, DomainEventPublisher> pair = TestUtils.createEventStoreAndPublisher();
        InMemoryEventStore inMemoryEventStore = pair.getFirst();
        inMemoryEventStore.store(new CargoBookedEvent(cargoTrackingId, payer, recipient, departure, arrival));
        CargoLoadingService cargoLoadingService = new CargoLoadingService(pair.getSecond(), new CargoRepository(inMemoryEventStore));

        ZonedDateTime cargoLoadingTime = ZonedDateTime.now();

        VesselVoyage vesselVoyage = new VesselVoyage(IdStringGenerator.nextIdString(), Type.SEA);

        CargoLoadedOnVesselEvent expectedEvent = new CargoLoadedOnVesselEvent(cargoTrackingId, cargoLoadingTime, departure, vesselVoyage);

        // WHEN
        cargoLoadingService.loadCargo(cargoTrackingId, cargoLoadingTime, departure, vesselVoyage);

        // THEN
        assertThat(inMemoryEventStore.getEvents()).contains(expectedEvent);
    }
}