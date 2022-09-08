package io.github.unhurried.example.backend.quarkus.resource.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

import io.github.unhurried.example.backend.quarkus.resource.bean.ErrorBean;

public class ResourceException extends WebApplicationException {
    private final Status status;
    private final ErrorBean body;

    public ResourceException(Status status, ErrorBean body) {
        this.status = status;
        this.body = body;
    }

    public Status getStatus() {
        return this.status;
    }

    public ErrorBean getBody() {
        return this.body;
    }
}
