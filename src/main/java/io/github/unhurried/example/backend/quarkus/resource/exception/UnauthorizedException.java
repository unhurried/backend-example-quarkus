package io.github.unhurried.example.backend.quarkus.resource.exception;

import javax.ws.rs.core.Response.Status;

import io.github.unhurried.example.backend.quarkus.resource.bean.ErrorBean;

public class UnauthorizedException extends ResourceException {
    public UnauthorizedException() {
        super(Status.UNAUTHORIZED, new ErrorBean("unauthorized", "Authentication failed."));
    }
}
