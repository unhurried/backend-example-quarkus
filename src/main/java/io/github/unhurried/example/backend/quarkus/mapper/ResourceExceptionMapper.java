package io.github.unhurried.example.backend.quarkus.mapper;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.ExceptionMapper;

import io.github.unhurried.example.backend.quarkus.resource.exception.ResourceException;

@Provider
@Priority(Priorities.AUTHENTICATION)
class ResourceExceptionMapper implements ExceptionMapper<ResourceException> {
    @Override
    public Response toResponse(ResourceException e) {
        return Response.status(e.getStatus()).entity(e.getBody()).build();
    }
}
