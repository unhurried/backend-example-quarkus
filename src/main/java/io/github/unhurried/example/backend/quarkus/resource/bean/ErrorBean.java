package io.github.unhurried.example.backend.quarkus.resource.bean;

public class ErrorBean {
    public String error;
    public String message;

    public ErrorBean(String error, String message) {
        this.error = error;
        this.message = message;
    }
}
