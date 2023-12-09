package com.example.rest;

import jakarta.validation.constraints.NotBlank;

public record CreateTodoCommand(
        @NotBlank
        String title
) {
        public static CreateTodoCommand of(String _title) {
                return new CreateTodoCommand(_title);
        }
}
