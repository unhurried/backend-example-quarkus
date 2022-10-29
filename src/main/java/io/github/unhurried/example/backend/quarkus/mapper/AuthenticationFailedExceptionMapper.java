package io.github.unhurried.example.backend.quarkus.mapper;

import javax.annotation.Priority;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.Priorities;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.ExceptionMapper;

import io.github.unhurried.example.backend.quarkus.resource.bean.ErrorBean;
import io.quarkus.security.AuthenticationFailedException;

@Provider
@Priority(Priorities.AUTHENTICATION)
class AuthenticationFailedExceptionMapper implements ExceptionMapper<AuthenticationFailedException> {

    @Override
    public Response toResponse(AuthenticationFailedException e) {
        return Response.status(Status.UNAUTHORIZED).entity(
            new ErrorBean("authentication_failed", e.getMessage())
        ).build();
    }
}
