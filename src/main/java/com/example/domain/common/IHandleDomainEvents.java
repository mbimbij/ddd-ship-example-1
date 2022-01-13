package com.example.domain.common;


public interface IHandleDomainEvents {
    void handle(HandlingEvent handlingEvent);
    boolean accept(HandlingEvent handlingEvent);
}
