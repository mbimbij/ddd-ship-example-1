package com.example.domain.common;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

@Getter
@EqualsAndHashCode
@ToString
public class ValueObject<T> {
    private final T value;

    public ValueObject(T value) {
        this.value = value;
    }

    public boolean sameAs(ValueObject<T> other) {
        return Objects.equals(value, other.value);
    }
}
