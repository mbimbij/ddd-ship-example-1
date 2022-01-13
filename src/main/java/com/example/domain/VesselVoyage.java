package com.example.domain;

import com.example.domain.common.Entity;
import com.example.domain.common.Id;
import lombok.EqualsAndHashCode;
import lombok.Value;

@EqualsAndHashCode(callSuper = true)
@Value
public class VesselVoyage extends Entity<String> {
    Type type;

    public VesselVoyage(Id<String> id, Type type) {
        super(id);
        this.type = type;
    }

    public enum Type {
        SEA, RAIL, TRUCK
    }
}
