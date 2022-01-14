package com.example.application.port.in;

import com.example.domain.CargoTrackingId;
import com.example.domain.Customer;
import com.example.domain.Location;

import java.time.ZonedDateTime;

public interface ISignOffCargoReception {
    void signOff(Customer recipient, CargoTrackingId cargoTrackingId, Location location, ZonedDateTime receptionTimestamp);
}
