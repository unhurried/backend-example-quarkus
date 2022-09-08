package io.github.unhurried.example.backend.quarkus.resource.bean;

import lombok.Value;

@Value
public class ErrorBean {
    private final String error;
    private final String message;
}
