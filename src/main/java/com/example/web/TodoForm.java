package com.example.web;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;


public class TodoForm {
    UUID id;
    
    @NotBlank
    String title;

    public TodoForm(UUID id, String title) {
        this.id = id;
        this.title = title;
    }

    public TodoForm() {
    }
    

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "TodoForm{" + "id=" + id + ", title=" + title + '}';
    }
    
    
}

//public record TodoForm(
//        UUID id,
//        @NotBlank
//        String title
//) {
//}

