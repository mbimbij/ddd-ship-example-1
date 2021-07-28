package com.example.domain;

import lombok.Value;

@Value
public class HandlingEvent {
    Cargo handledCargo;
    CarrierMovement carrierMovement;
}
