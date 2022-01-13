package com.example.domain.common;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class Id<T> extends ValueObject<T> {
    public Id(T value) {
        super(value);
    }
}
