package com.example.domain;

import lombok.Getter;

@Getter
public class Location {
    private String portCode;

    public Location(String portCode) {
        this.portCode = portCode;
    }
}
