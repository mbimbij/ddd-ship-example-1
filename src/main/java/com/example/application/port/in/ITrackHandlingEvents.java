package com.example.application.port.in;

import com.example.domain.CargoTrackingId;
import com.example.domain.DeliveryHistory;

import java.util.Optional;

public interface ITrackHandlingEvents {
    Optional<DeliveryHistory> track(CargoTrackingId cargoTrackingId);
}
