package fr.kaibee.todoList.controller.dto;

import javax.validation.constraints.NotBlank;
import java.beans.ConstructorProperties;

public final class TaskCreateDto {
    @NotBlank(message = "Task name must not be empty")
    private final String label;

    @ConstructorProperties({"label"})
    public TaskCreateDto(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
