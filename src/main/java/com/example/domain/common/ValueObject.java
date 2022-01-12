package com.example.domain.common;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class ValueObject<T> {
    private final T value;

    public ValueObject(T value) {
        this.value = value;
    }
}
