package io.github.unhurried.example.backend.quarkus.mapper;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.ExceptionMapper;

import io.github.unhurried.example.backend.quarkus.resource.bean.ErrorBean;
import io.quarkus.logging.Log;

@Provider
@Priority(Priorities.AUTHENTICATION)
class ThrowableExceptionMapper implements ExceptionMapper<Throwable> {
    @Override
    public Response toResponse(Throwable t) {
        Log.error(t);
        return Response.status(Status.INTERNAL_SERVER_ERROR).entity(new ErrorBean("internal_server_error", "Something wrong happend.")).build();
    }
}
