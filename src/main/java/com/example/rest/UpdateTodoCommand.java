package com.example.rest;

import jakarta.validation.constraints.NotBlank;

public record UpdateTodoCommand(
        @NotBlank
        String title
) {
}
