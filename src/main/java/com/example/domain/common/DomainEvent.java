package com.example.domain.common;

public abstract class DomainEvent {
    protected final Id<String> id;

    protected DomainEvent(Id<String> id) {
        this.id = id;
    }
}
