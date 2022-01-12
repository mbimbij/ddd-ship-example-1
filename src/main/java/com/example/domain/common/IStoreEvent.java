package com.example.domain.common;

import com.example.domain.common.DomainEvent;
import com.example.domain.common.IHandleDomainEvents;

public interface IStoreEvent extends IHandleDomainEvents {
    void store(DomainEvent domainEvent);
}
