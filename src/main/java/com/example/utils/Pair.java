package com.example.utils;

import lombok.Value;

@Value
public class Pair<T, S> {
    T first;
    S second;
}
