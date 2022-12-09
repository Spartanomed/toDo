package fr.kaibee.todoList.repository;

import fr.kaibee.todoList.domain.model.Task;

import java.util.List;
import java.util.UUID;

public interface TaskRepository {
    void save(Task task);
    List<Task> findByToDoId(UUID toDoId);
    Task findTaskById(UUID id);
    void update(UUID id, Task taskToUpdate);
}
