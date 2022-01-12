package com.example.domain;

import com.example.domain.common.Id;
import lombok.Value;

@Value
public class Customer {
    Id<String> customerId;

    public Customer(String customerId) {
        this.customerId = new Id<>(customerId);
    }
}
