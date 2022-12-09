package fr.kaibee.todoList.controller;

import fr.kaibee.todoList.controller.dto.TaskCreateDto;
import fr.kaibee.todoList.controller.dto.TaskDto;
import fr.kaibee.todoList.controller.mapper.TaskMapper;
import fr.kaibee.todoList.domain.model.Task;
import fr.kaibee.todoList.domain.service.TaskService;
import fr.kaibee.todoList.domain.service.ToDoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/todo/{idTodo}/task")
public class TaskController {
    private final TaskService taskService;
    private final ToDoService toDoService;

    private final TaskMapper taskMapper;

    public TaskController(TaskService taskService, ToDoService toDoService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.toDoService = toDoService;
        this.taskMapper = taskMapper;
    }

    @PutMapping("/{id}")
    public TaskDto updateTask(@PathVariable UUID id, @RequestBody TaskDto task) {
        Task taskToUpdate = taskMapper.mapTo(task);
        return taskMapper.mapTo(taskService.updateTask(id, taskToUpdate));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDto create(@PathVariable UUID idTodo, @RequestBody @Valid TaskCreateDto taskCreateDto) {
            Task taskToCreate = taskMapper.mapTo(taskCreateDto);
            return taskMapper.mapTo(toDoService.addTask(idTodo, taskToCreate));
    }
}
