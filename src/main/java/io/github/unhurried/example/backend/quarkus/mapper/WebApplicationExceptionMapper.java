package io.github.unhurried.example.backend.quarkus.mapper;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.ExceptionMapper;

import io.github.unhurried.example.backend.quarkus.resource.bean.ErrorBean;

@Provider
@Priority(Priorities.AUTHENTICATION)
class WebApplicationExceptionMapper implements ExceptionMapper<WebApplicationException> {
    @Override
    public Response toResponse(WebApplicationException e) {
        return Response.status(e.getResponse().getStatus()).entity(new ErrorBean("web_application_error", "Something wrong happened.")).build();
    }
}
