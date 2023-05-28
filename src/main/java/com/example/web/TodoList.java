package com.example.web;

import com.example.domain.Todo;
import com.example.ejb.EjbTodoRepository;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jdk.jfr.Name;

import java.io.Serializable;
import java.util.List;

@ViewScoped
@Name("todos")
public class TodoList implements Serializable {

    @Inject
    EjbTodoRepository todoRepository;

    List<Todo> todos;

    TodoForm form;

    public List<Todo> getTodos() {
        return todos;
    }

    public void setTodos(List<Todo> todos) {
        this.todos = todos;
    }

    public TodoForm getForm() {
        return form;
    }

    public void setForm(TodoForm form) {
        this.form = form;
    }

    public void loadTodos() {
        this.todos = todoRepository.findAll();
    }

    public void addTodo(TodoForm form) {
        todoRepository.save(Todo.of(form.title()));
        loadTodos();
    }

    public void editTodo(Todo todo) {
        form = new TodoForm(todo.getId(), todo.getTitle());
    }

    public void updateTodo(TodoForm form) {
        var existed = todoRepository.findById(form.id());
        existed.setTitle(form.title());
        todoRepository.save(existed);
        loadTodos();
    }

    public void deleteTodo(Todo todo) {
        todoRepository.deleteById(todo.getId());
        loadTodos();
    }

    public void markAsCompleted(Todo todo) {
        todoRepository.markAsCompleted(todo.getId());
        loadTodos();
    }

    public void markAsUnCompleted(Todo todo) {
        todoRepository.markAsUnCompleted(todo.getId());
        loadTodos();
    }

}
