package io.github.unhurried.example.backend.quarkus.resource;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;

import io.github.unhurried.example.backend.quarkus.entity.TodoEntity;
import io.github.unhurried.example.backend.quarkus.interceptor.Logged;
import io.github.unhurried.example.backend.quarkus.resource.bean.ListParam;
import io.github.unhurried.example.backend.quarkus.resource.bean.TodoBean;
import io.github.unhurried.example.backend.quarkus.resource.bean.TodoListBean;
import io.github.unhurried.example.backend.quarkus.resource.bean.TodoBean.Category;
import io.github.unhurried.example.backend.quarkus.resource.exception.UnauthorizedException;
import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.panache.common.Page;
import io.quarkus.security.Authenticated;
import io.smallrye.mutiny.Uni;

@Logged
@Authenticated
@Path("todos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TodoResource {

    @Inject
    SecurityContext sContext;

    private String getUserId() {
        final var sub = this.sContext.getUserPrincipal().getName();
        if (sub == null) throw new UnauthorizedException();
        return sub;
    }

    @GET
    public Uni<TodoListBean> list(@BeanParam ListParam param) {
        final String userId = getUserId();
        return Panache.withTransaction(() -> {
            var query = TodoEntity.find("userId", userId);
            var total = query.count();
            Uni<List<TodoEntity>> items = query.page(Page.of(param.page, param.size)).list();

            return Uni.combine().all().unis(total, items).asTuple().<TodoListBean>map(t -> { 
                var bean = new TodoListBean();
                bean.total = Long.valueOf(t.getItem1());
                bean.items = t.getItem2().stream().map(this::entityToResource).collect(Collectors.toList());
                return bean;
            });
        });
    }

    @POST
    public Uni<Response> create(@Valid TodoBean req) {
        var entity = resourceToEntity(req, getUserId());
        var resBody = Panache.<TodoEntity>withTransaction(entity::persist).map(this::entityToResource);
        return resBody.map(b -> { return Response.status(Status.CREATED).entity(b).build(); });
    } 

    @GET
    @Path("{id}")
    public Uni<TodoBean> get(@PathParam("id") String id) {
        var uuid = UUID.fromString(id);
        return TodoEntity.<TodoEntity>find("id = ?1 and userId = ?2", uuid, getUserId()).firstResult().map(entity -> {
            if (entity == null) {
                throw new NotFoundException();
            } else {
                return entityToResource(entity);
            }
        });
    }

    @PUT
    @Path("{id}")
    public Uni<TodoBean> update(@PathParam("id") String id, @Valid TodoBean req) {
        final String userId = getUserId();
        var uuid = UUID.fromString(id);
        return Panache.<TodoEntity>withTransaction(() -> {
            return TodoEntity.<TodoEntity>find("id = ?1 and userId = ?2", uuid, userId).firstResult()
                .onItem().ifNull().continueWith(() -> { throw new NotFoundException(); })
                .onItem().ifNotNull().transformToUni(entity -> {
                    entity.title = req.title;
                    for (var c: Category.values()) {
                        if (c.toString().equals(req.category)) entity.category = c;
                    }
                    entity.content = req.content;
                    return entity.persist();
                });
        }).onItem().transform(entity -> {
            return entityToResource(entity);
        });
    }

    @DELETE
    @Path("{id}")
    public Uni<Void> delete(@PathParam("id") String id) {
        final String userId = getUserId();
        var uuid = UUID.fromString(id);
        return Panache.withTransaction(() -> {
            return TodoEntity.delete("id = ?1 and userId = ?2", uuid, userId);
        }).onItem().transform(b -> {
            if(b == 1) {
                return null;
            } else {
                throw new NotFoundException();
            }
        });
    }

    private TodoEntity resourceToEntity(TodoBean resource, String userId) {
        var entity = new TodoEntity();
        entity.title = resource.title;
        for (var c: Category.values()) {
            if (c.toString().equals(resource.category)) entity.category = c;
        }
        entity.content = resource.content;
        entity.userId = userId;
        return entity;
    }

    private TodoBean entityToResource(TodoEntity entity) {
        var resource = new TodoBean();
        resource.id = entity.id.toString();
        resource.title = entity.title;
        resource.category = entity.category.toString();
        resource.content = entity.content;
        return resource;
    }
}
