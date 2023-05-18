package com.example.domain;

import com.example.repository.TodoRepository;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Startup
@Singleton
public class DataInitializer {
    private static Logger LOG = Logger.getLogger(DataInitializer.class.getName());
    @Inject
    TodoRepository todoRepository;

    @PostConstruct
    public void init(){
       LOG.log(Level.SEVERE, "initializing sample data");
       todoRepository.deleteAll();
       todoRepository.saveAll(List.of(Todo.of("Say Hello to Jakarta EE 10")));
    }
}
