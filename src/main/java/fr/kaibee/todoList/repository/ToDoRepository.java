package fr.kaibee.todoList.repository;

import fr.kaibee.todoList.domain.model.Task;
import fr.kaibee.todoList.domain.model.ToDo;

import java.util.List;
import java.util.UUID;

public interface ToDoRepository {
    void save(ToDo toDo);
    ToDo findById(UUID id);

    void addTask(UUID id, Task task);

    List<ToDo> findAll();
}
