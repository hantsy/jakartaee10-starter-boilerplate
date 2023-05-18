package com.example.web;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record TodoForm(
        UUID id,
        @NotBlank
        String title
) {
}
