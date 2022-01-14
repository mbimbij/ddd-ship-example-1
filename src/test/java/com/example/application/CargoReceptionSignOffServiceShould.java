package com.example.application;

import com.example.adapter.out.InMemoryEventStore;
import com.example.domain.Location;
import com.example.domain.VesselVoyage;
import com.example.domain.booking.CargoBookedEvent;
import com.example.domain.loading.CargoLoadedOnVesselEvent;
import com.example.domain.loading.CargoUnloadedFromVesselEvent;
import com.example.domain.signoff.CargoNotAtDestinationException;
import com.example.domain.signoff.CargoReceptionSignedOffEvent;
import com.example.domain.CargoRepository;
import com.example.domain.CargoTrackingId;
import com.example.domain.Customer;
import com.example.domain.common.DomainEventPublisher;
import com.example.domain.common.IdStringGenerator;
import com.example.utils.Pair;
import com.example.utils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;

import static com.example.domain.VesselVoyage.Type.SEA;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CargoReceptionSignOffServiceShould {
    private final Customer recipient = new Customer("recipient");
    private final CargoTrackingId cargoTrackingId = new CargoTrackingId("id");
    private final ZonedDateTime receptionTimestamp = ZonedDateTime.now();
    private final Location departure = new Location("departure");
    private final Location arrival = new Location("arrival");
    private InMemoryEventStore inMemoryEventStore;
    private DomainEventPublisher eventPublisher;
    private CargoReceptionSignOffService cargoReceptionSignOffService;

    @BeforeEach
    void setUp() {
        String id = "id";
        IdStringGenerator.setNextStringGenerator(() -> id);

        Pair<InMemoryEventStore, DomainEventPublisher> pair = TestUtils.createEventStoreAndPublisher();
        eventPublisher = pair.getSecond();
        inMemoryEventStore = pair.getFirst();

        inMemoryEventStore.store(new CargoBookedEvent(cargoTrackingId, new Customer("payer"), recipient, departure, arrival));

        cargoReceptionSignOffService = new CargoReceptionSignOffService(eventPublisher, new CargoRepository(inMemoryEventStore));
    }

    @Test
    void signOffCargoReception_whenCargoAtDestination() {
        // WHEN
        CargoSignoffCommand cargoSignoffCommand = new CargoSignoffCommand(recipient, cargoTrackingId, arrival, receptionTimestamp);
        cargoReceptionSignOffService.signOff(cargoSignoffCommand);

        // THEN
        CargoReceptionSignedOffEvent expectedEvent = new CargoReceptionSignedOffEvent(recipient, cargoTrackingId, receptionTimestamp);
        assertThat(inMemoryEventStore.getEvents()).contains(expectedEvent);
    }

    @Test
    void throwException_whenSigningOff_andCargoNotAtDestination() {
        // GIVEN
        Location intermediate = new Location("intermediate");
        VesselVoyage vesselVoyage = new VesselVoyage(IdStringGenerator.nextIdString(), SEA);
        inMemoryEventStore.store(new CargoLoadedOnVesselEvent(cargoTrackingId, ZonedDateTime.now().plusDays(1), departure, vesselVoyage));
        inMemoryEventStore.store(new CargoUnloadedFromVesselEvent(cargoTrackingId, ZonedDateTime.now().plusDays(2), intermediate, vesselVoyage));

        // WHEN
        CargoSignoffCommand cargoSignoffCommand = new CargoSignoffCommand(recipient, cargoTrackingId, intermediate, receptionTimestamp);
        assertThatThrownBy(() -> cargoReceptionSignOffService.signOff(cargoSignoffCommand)).isInstanceOf(CargoNotAtDestinationException.class);

        // THEN
        assertThat(inMemoryEventStore.getEvents()).noneMatch(handlingEvent -> handlingEvent instanceof CargoReceptionSignedOffEvent);
    }
}