package com.example.application;

import com.example.application.adapter.out.InMemoryEventStore;
import com.example.domain.CargoTrackingId;
import com.example.domain.Customer;
import com.example.domain.Location;
import com.example.domain.booking.CargoBookedEvent;
import com.example.domain.booking.CargoFactory;
import com.example.domain.common.DomainEventPublisher;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class CargoBookingServiceShould {
    @Test
    void allowACustomerToBookACargo() {
        // GIVEN
        InMemoryEventStore eventStore = new InMemoryEventStore();
        DomainEventPublisher eventPublisher = new DomainEventPublisher();
        eventPublisher.subscribe(eventStore);
        CargoBookingService cargoBookingService = new CargoBookingService(eventPublisher, new CargoFactory());
        Customer payer = new Customer("payer");
        Customer recipient = new Customer("recipient");
        Location departure = new Location("departure");
        Location arrival = new Location("arrival");
        CargoBookingCommand cargoBookingCommand = new CargoBookingCommand(payer,
                                                                          recipient,
                                                                          departure,
                                                                          arrival);

        // WHEN
        CargoTrackingId cargoTrackingId = cargoBookingService.book(cargoBookingCommand);

        // THEN
        CargoBookedEvent expectedCargoBookedEvent = new CargoBookedEvent(cargoTrackingId, payer, recipient, departure, arrival);
        Assertions.assertThat(eventStore.getEvents())
                  .usingRecursiveFieldByFieldElementComparatorIgnoringFields("id")
                  .containsExactly(expectedCargoBookedEvent);
    }
}