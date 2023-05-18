package com.example.rest;


import com.example.domain.Todo;
import com.example.domain.TodoNotFoundException;
import com.example.repository.TodoRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.net.URI;
import java.util.UUID;

@RequestScoped
@Path("todos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TodoResource {

    @Inject
    TodoRepository todoRepository;

    @Context
    UriInfo uriInfo;

    @GET
    public Response getAll(@QueryParam("title") String title) {
        var todos = todoRepository.findByTitle(title);
        return Response.ok(todos).build();
    }

    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") UUID id) {
        return todoRepository.findOptionalById(id)
                .map(todo -> Response.ok(todo).build())
                .orElseThrow(() -> new TodoNotFoundException(id));
    }

    @POST
    public Response saveTodo(@Valid CreateTodoCommand data) {
        var saved = todoRepository.save(Todo.of(data.title()));
        return Response.created(URI.create(uriInfo.getBaseUri() + "/todos/" + saved.getId())).build();
    }

    @PUT
    @Path("{id}")
    public Response updateById(@PathParam("id") UUID id, @Valid UpdateTodoCommand data) {
        return todoRepository.findOptionalById(id)
                .map(todo -> {
                    todo.setTitle(data.title());
                    return Response.noContent().build();
                })
                .orElseThrow(() -> new TodoNotFoundException(id));
    }

    @DELETE
    @Path("{id}")
    public Response deleteById(@PathParam("id") UUID id) {
        todoRepository.deleteById(id);
        return Response.noContent().build();
    }

    @POST
    @Path("{id}/completed")
    public Response markAsCompleted(@PathParam("id") UUID id) {
        todoRepository.markAsCompleted(id);
        return Response.noContent().build();
    }

    @DELETE
    @Path("{id}/completed")
    public Response markAsUnCompleted(@PathParam("id") UUID id) {
        todoRepository.markAsUnCompleted(id);
        return Response.noContent().build();
    }

}
