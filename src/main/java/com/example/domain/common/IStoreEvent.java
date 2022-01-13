package com.example.domain.common;

import java.util.List;

public interface IStoreEvent extends IHandleDomainEvents {
    void store(HandlingEvent handlingEvent);
    List<HandlingEvent> findById(Id<String> id);
}
