package fr.kaibee.todoList.controller;

import fr.kaibee.todoList.controller.dto.ToDoCreateDto;
import fr.kaibee.todoList.controller.dto.ToDoDto;
import fr.kaibee.todoList.controller.dto.ToDosDto;
import fr.kaibee.todoList.controller.mapper.ToDoMapper;
import fr.kaibee.todoList.domain.model.ToDo;
import fr.kaibee.todoList.domain.service.ToDoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/todo")
public class ToDoController {
    private final ToDoService toDoService;
    private final ToDoMapper toDoMapper;

    public ToDoController(ToDoService toDoService, ToDoMapper toDoMapper) {
        this.toDoService = toDoService;
        this.toDoMapper = toDoMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ToDoDto create(@RequestBody @Valid ToDoCreateDto toDoCreateDto) {
        ToDo toDoToCreate = toDoMapper.mapTo(toDoCreateDto);
        return toDoMapper.mapTo(toDoService.create(toDoToCreate));
    }

    @GetMapping("/{id}")
    public ToDoDto findById(@PathVariable UUID id) {
        return toDoMapper.mapTo(toDoService.findById(id));
    }

    @GetMapping
    public ToDosDto findAll() {
        List<ToDoDto> toDos = toDoMapper.mapTo(toDoService.findAll());
        return new ToDosDto(toDos);
    }
}
