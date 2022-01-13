package com.example.domain.common;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public abstract class HandlingEvent {
    protected final Id<String> id;

    protected HandlingEvent(Id<String> id) {
        this.id = id;
    }
}
