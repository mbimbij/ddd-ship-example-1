package com.example.domain;

import lombok.Value;

@Value
public class HandlingEvent {
    CargoDelivery handledCargoDelivery;
    CarrierMovement carrierMovement;
}
