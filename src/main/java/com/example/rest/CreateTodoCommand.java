package com.example.rest;

import jakarta.validation.constraints.NotBlank;

public record CreateTodoCommand(
        @NotBlank
        String title
) {
}
