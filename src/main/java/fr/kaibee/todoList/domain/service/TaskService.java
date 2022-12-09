package fr.kaibee.todoList.domain.service;

import fr.kaibee.todoList.domain.model.Task;
import fr.kaibee.todoList.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task updateTask(UUID id, Task task) {
        Task taskToUpdate = taskRepository.findTaskById(id);
        taskToUpdate.setStatus(task.getStatus());
        this.taskRepository.update(id, taskToUpdate);
        return taskToUpdate;

    }
}
