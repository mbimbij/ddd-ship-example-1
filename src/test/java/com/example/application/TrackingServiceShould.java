package com.example.application;

import com.example.adapter.out.InMemoryEventStore;
import com.example.domain.CargoTrackingId;
import com.example.domain.Customer;
import com.example.domain.DeliveryHistory;
import com.example.domain.Location;
import com.example.domain.VesselVoyage;
import com.example.domain.booking.CargoBookedEvent;
import com.example.domain.common.DomainEventPublisher;
import com.example.domain.common.IdStringGenerator;
import com.example.domain.loading.CargoLoadedOnVesselEvent;
import com.example.domain.loading.CargoUnloadedFromVesselEvent;
import com.example.domain.signoff.CargoReceptionSignedOffEvent;
import com.example.utils.Pair;
import com.example.utils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import static com.example.domain.VesselVoyage.Type.SEA;
import static org.assertj.core.api.Assertions.assertThat;

class TrackingServiceShould {

    private final Customer recipient = new Customer("recipient");
    private final CargoTrackingId cargoTrackingId = new CargoTrackingId("id");
    private final Location departure = new Location("departure");
    private final Location arrival = new Location("arrival");
    private InMemoryEventStore inMemoryEventStore;
    private TrackingService trackingService;

    @BeforeEach
    void setUp() {
        String id = "id";
        IdStringGenerator.setNextStringGenerator(() -> id);

        Pair<InMemoryEventStore, DomainEventPublisher> pair = TestUtils.createEventStoreAndPublisher();
        inMemoryEventStore = pair.getFirst();

        trackingService = new TrackingService(inMemoryEventStore);
    }

    @Test
    void returnsPastHandlingEvents() {
        // GIVEN
        VesselVoyage vesselVoyage = new VesselVoyage(IdStringGenerator.nextIdString(), SEA);
        ZonedDateTime cargoLoadingTime = ZonedDateTime.now().plusDays(1);
        ZonedDateTime cargoUnloadingTime = ZonedDateTime.now().plusDays(2);
        ZonedDateTime receptionTimestamp = cargoUnloadingTime.plusHours(1);

        inMemoryEventStore.store(new CargoBookedEvent(cargoTrackingId, new Customer("payer"), recipient, departure, arrival));
        inMemoryEventStore.store(new CargoLoadedOnVesselEvent(cargoTrackingId, cargoLoadingTime, departure, vesselVoyage));
        inMemoryEventStore.store(new CargoUnloadedFromVesselEvent(cargoTrackingId, cargoUnloadingTime, arrival, vesselVoyage));
        inMemoryEventStore.store(new CargoReceptionSignedOffEvent(recipient, cargoTrackingId, receptionTimestamp));

        // WHEN
        Optional<DeliveryHistory> deliveryHistory = trackingService.track(cargoTrackingId);

        // THEN
        DeliveryHistory expectedDeliveryHistory = new DeliveryHistory(List.of(new CargoBookedEvent(cargoTrackingId, new Customer("payer"), recipient, departure, arrival),
                                                                              new CargoLoadedOnVesselEvent(cargoTrackingId, cargoLoadingTime, departure, vesselVoyage),
                                                                              new CargoUnloadedFromVesselEvent(cargoTrackingId, cargoUnloadingTime, arrival, vesselVoyage),
                                                                              new CargoReceptionSignedOffEvent(recipient, cargoTrackingId, receptionTimestamp)));
        assertThat(deliveryHistory).hasValue(expectedDeliveryHistory);
    }
}