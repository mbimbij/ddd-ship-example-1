package com.example.domain;

import com.example.domain.common.Id;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class CargoTrackingId extends Id<String> {
    public CargoTrackingId(String value) {
        super(value);
    }
}
