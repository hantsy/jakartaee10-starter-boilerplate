package com.example.rest;

import com.example.domain.TodoNotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class TodoNotFoundExceptionMapper implements ExceptionMapper<TodoNotFoundException> {
    @Override
    public Response toResponse(TodoNotFoundException exception) {
        return Response.status(Response.Status.NOT_FOUND).entity(exception.getMessage()).build();
    }
}
