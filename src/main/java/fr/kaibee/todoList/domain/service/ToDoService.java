package fr.kaibee.todoList.domain.service;

import fr.kaibee.todoList.domain.model.Task;
import fr.kaibee.todoList.domain.model.ToDo;
import fr.kaibee.todoList.repository.ToDoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

public class ToDoService {
    private final ToDoRepository toDoRepository;

    public ToDoService(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }
    public ToDo create(ToDo toDo) {
        toDoRepository.save(toDo);
        return toDo;
    }
    public ToDo findById(UUID id) {
        return toDoRepository.findById(id);
    }

    public Task addTask(UUID id, Task taskToCreate) {
        toDoRepository.addTask(id, taskToCreate);
        return taskToCreate;
    }

    public List<ToDo> findAll() {
        return toDoRepository.findAll();
    }
}
