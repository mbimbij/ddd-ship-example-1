package com.example.domain;

import lombok.Value;

@Value
public class DeliverySpecification {
    Location departure;
    Location arrival;
}
