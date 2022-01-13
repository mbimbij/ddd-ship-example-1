package com.example.domain.common;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Entity<TId> {
    protected final Id<TId> id;

    public Entity(Id<TId> id) {
        this.id = id;
    }
}
