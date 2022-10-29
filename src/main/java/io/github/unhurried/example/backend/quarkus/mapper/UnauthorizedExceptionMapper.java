package io.github.unhurried.example.backend.quarkus.mapper;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.ExceptionMapper;

import io.github.unhurried.example.backend.quarkus.resource.bean.ErrorBean;
import io.quarkus.security.UnauthorizedException;

@Provider
@Priority(Priorities.AUTHENTICATION)
class UnauthorizedExceptionMapper implements ExceptionMapper<UnauthorizedException> {

    @Override
    public Response toResponse(UnauthorizedException e) {
        return Response.status(Status.UNAUTHORIZED).entity(new ErrorBean("unauthorized", e.getMessage())).build();
    }
}
