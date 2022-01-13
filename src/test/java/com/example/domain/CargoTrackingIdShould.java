package com.example.domain;

import com.example.domain.common.Id;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CargoTrackingIdShould {
    @Test
    void beEqualToAnId() {
        // GIVEN
        String value = "id";
        CargoTrackingId cargoTrackingId = new CargoTrackingId(value);
        Id<String> id = new Id<>(value);

        // THEN
        assertThat(cargoTrackingId.sameAs(id)).isTrue();
    }
}