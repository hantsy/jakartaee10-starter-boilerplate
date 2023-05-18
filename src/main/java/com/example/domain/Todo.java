package com.example.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "todos")
public class Todo extends AbstractEntity<UUID> {
    private String title;

    private Boolean completed = Boolean.FALSE;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public Todo() {
    }

    public static Todo of(String title) {
        var todo = new Todo();
        todo.setTitle(title);
        return todo;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Todo)) return false;
        Todo todo = (Todo) o;
        return Objects.equals(title, todo.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id = " + this.getId() +
                ", title='" + title + "'" +
                ", completed=" + completed +
                ", createdAt=" + this.getCreatedAt() +
                ", lastModifiedAt=" + this.getLastModifiedAt() +
                '}';
    }
}
