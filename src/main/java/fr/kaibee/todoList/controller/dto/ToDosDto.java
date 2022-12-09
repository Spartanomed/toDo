package fr.kaibee.todoList.controller.dto;

import java.util.List;

public final class ToDosDto {
    private final List<ToDoDto> toDos;

    public ToDosDto(List<ToDoDto> toDos) {
        this.toDos = toDos;
    }

    public List<ToDoDto> getToDos() {
        return toDos;
    }
}
