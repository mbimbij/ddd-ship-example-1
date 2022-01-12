package com.example.domain.common;

public interface IHandleDomainEvents {
    void handle(DomainEvent domainEvent);
    boolean accept(DomainEvent domainEvent);
}
