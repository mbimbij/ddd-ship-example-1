package com.example.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Cargo {
    private TrackingId trackingId;
    private Location departure;
    private Location arrival;
}
