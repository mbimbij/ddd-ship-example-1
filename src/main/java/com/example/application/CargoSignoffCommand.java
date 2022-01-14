package com.example.application;

import com.example.domain.CargoTrackingId;
import com.example.domain.Customer;
import com.example.domain.Location;

import java.time.ZonedDateTime;

public record CargoSignoffCommand(Customer recipient, CargoTrackingId cargoTrackingId, Location location,
                                  ZonedDateTime receptionTimestamp) {
}