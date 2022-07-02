package io.github.unhurried.example.backend.quarkus.mapper;

import javax.annotation.Priority;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Priorities;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.ExceptionMapper;

import io.github.unhurried.example.backend.quarkus.resource.bean.ErrorBean;

@Provider
@Priority(Priorities.AUTHENTICATION)
class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {
    @Override
    public Response toResponse(NotFoundException e) {
        return Response.status(Status.NOT_FOUND).entity(new ErrorBean("not_found", "The resource you are looking for doesn't exist.")).build();
    }
}
