package com.example.web;

import com.example.domain.Todo;
import com.example.ejb.EjbTodoRepository;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@ViewScoped
@Named("todoList")
public class TodoList implements Serializable {
    private static final Logger LOGGER= Logger.getLogger(TodoList.class.getName());
    
    @Inject
    FacesContext facesContext;

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
    
    public void init(){
        LOGGER.info("init...");
        loadTodos();
        this.form = new TodoForm();
    }

    public void loadTodos() {
        this.todos = todoRepository.findAll();
    }

    public void saveTodo() {
        LOGGER.log(Level.INFO, "saving form:{0}", new Object[]{this.form});
        if (this.form.getId() == null) {
            todoRepository.save(Todo.of(this.form.getTitle()));
        } else {
            var existed = todoRepository.findById(this.form.getId());
            existed.setTitle(this.form.getTitle());
            todoRepository.save(existed);
        }
        loadTodos();
        this.form = new TodoForm();
        facesContext.addMessage(null,new FacesMessage("Todo was saved sucessfully."));
    }

    public void editTodo(UUID id) {
        LOGGER.log(Level.INFO, "editting todo:{0}", new Object[]{id});
        var todo = todoRepository.findById(id);
        this.form = new TodoForm(todo.getId(), todo.getTitle());
    }

    public void deleteTodo(UUID id) {
        LOGGER.log(Level.INFO, "deleting todo:{0}", new Object[]{id});
        todoRepository.deleteById(id);
        loadTodos();
        facesContext.addMessage(null,new FacesMessage("Todo '%s' was deleted sucessfully.".formatted(id.toString())));
    }
    
    public void toggleCompletedStatus(UUID id) {
         LOGGER.log(Level.INFO, "toggle status:{0}", new Object[]{id});
        var todo = todoRepository.findById(id);
        if(todo.isCompleted()) {
            markAsUnCompleted(todo);
        }else{
            markAsCompleted(todo);
        }
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
