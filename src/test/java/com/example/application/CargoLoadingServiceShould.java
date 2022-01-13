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
import com.example.domain.loading.CargoUnloadedOnVesselEvent;
import com.example.utils.Pair;
import com.example.utils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class CargoLoadingServiceShould {

    private final CargoTrackingId cargoTrackingId = new CargoTrackingId("id");
    private final Customer payer = new Customer("payer");
    private final Customer recipient = new Customer("recipient");
    private final Location departure = new Location("departure");
    private final Location arrival = new Location("arrival");
    private InMemoryEventStore inMemoryEventStore;
    private DomainEventPublisher eventPublisher;
    private CargoLoadingService cargoLoadingService;

    @BeforeEach
    void setUp() {
        String id = "id";
        IdStringGenerator.setNextStringGenerator(() -> id);

        Pair<InMemoryEventStore, DomainEventPublisher> pair = TestUtils.createEventStoreAndPublisher();
        eventPublisher = pair.getSecond();
        inMemoryEventStore = pair.getFirst();

        cargoLoadingService = new CargoLoadingService(eventPublisher, new CargoRepository(inMemoryEventStore));
    }

    @Test
    void sendAnEvent_whenLoadingCargo() {
        // GIVEN
        inMemoryEventStore.store(new CargoBookedEvent(cargoTrackingId, payer, recipient, departure, arrival));

        ZonedDateTime cargoLoadingTime = ZonedDateTime.now();
        VesselVoyage vesselVoyage = new VesselVoyage(IdStringGenerator.nextIdString(), Type.SEA);
        CargoLoadedOnVesselEvent expectedEvent = new CargoLoadedOnVesselEvent(cargoTrackingId, cargoLoadingTime, departure, vesselVoyage);

        // WHEN
        cargoLoadingService.loadCargo(cargoTrackingId, cargoLoadingTime, departure, vesselVoyage);

        // THEN
        assertThat(inMemoryEventStore.getEvents()).contains(expectedEvent);
    }

    @Test
    void sendAnEvent_whenUnloadingCargo() {
        // GIVEN
        VesselVoyage vesselVoyage = new VesselVoyage(IdStringGenerator.nextIdString(), Type.SEA);
        ZonedDateTime cargoLoadingTime = ZonedDateTime.now();
        ZonedDateTime cargoUnloadingTime = cargoLoadingTime.plusDays(1);
        inMemoryEventStore.store(new CargoBookedEvent(cargoTrackingId, payer, recipient, departure, arrival));
        inMemoryEventStore.store(new CargoLoadedOnVesselEvent(cargoTrackingId,
                                                              cargoLoadingTime,
                                                              departure,
                                                              vesselVoyage));

        // WHEN
        cargoLoadingService.unloadCargo(cargoTrackingId, cargoUnloadingTime, arrival, vesselVoyage);

        // THEN
        assertThat(inMemoryEventStore.getEvents()).contains(new CargoUnloadedOnVesselEvent(cargoTrackingId,
                                                                                           cargoUnloadingTime,
                                                                                           arrival,
                                                                                           vesselVoyage));
    }
}