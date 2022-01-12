package com.example.domain.common;

public class Entity<TId> {
    protected final Id<TId> id;

    public Entity(Id<TId> id) {
        this.id = id;
    }
}
