package fr.kaibee.todoList.controller.dto;

import java.beans.ConstructorProperties;
import java.util.List;
import java.util.UUID;

public final class ToDoDto {
    private final UUID id;
    private final String title;
    private final List<TaskDto> tasks;
    @ConstructorProperties({"id", "title", "tasks"})
    public ToDoDto(UUID id, String title, List<TaskDto> tasks) {
        this.id = id;
        this.title = title;
        this.tasks = tasks;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<TaskDto> getTasks() {
        return tasks;
    }
}
