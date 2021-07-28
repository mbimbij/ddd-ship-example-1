package com.example.application;

import com.example.domain.Cargo;
import com.example.domain.CargoRepositoryInMemory;
import com.example.domain.Location;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class BookingApplicationTest {
    @Test
    void canBookANewCargoTrip() {
        // GIVEN
        CargoRepositoryInMemory cargoRepository = new CargoRepositoryInMemory();
        BookingApplication bookingApplication = new BookingApplication(cargoRepository);
        Location departure = new Location("departurePortCode");
        Location arrival = new Location("arrivalPortCode");
        Cargo expectedCargo = new Cargo(null, departure, arrival);

        // WHEN
        Cargo cargo = bookingApplication.bookNewCargo(departure, arrival);

        // THEN
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(cargo).usingRecursiveComparison().ignoringFields("trackingId").isEqualTo(expectedCargo);
            softAssertions.assertThat(cargo.getTrackingId()).isNotNull();
            softAssertions.assertThat(cargo.getTrackingId().getValue()).isNotBlank();
        });

        // WHEN
        Optional<Cargo> cargoFromRepository = cargoRepository.findByTrackingId(cargo.getTrackingId());

        // THEN
        assertThat(cargoFromRepository).hasValue(cargo);
    }
}