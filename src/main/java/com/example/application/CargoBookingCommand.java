package com.example.application;

import com.example.domain.Customer;
import com.example.domain.Location;
import lombok.Value;

@Value
public class CargoBookingCommand {
    Customer payer;
    Customer recipient;
    Location departure;
    Location arrival;
}
