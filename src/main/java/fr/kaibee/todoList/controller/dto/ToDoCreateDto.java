package fr.kaibee.todoList.controller.dto;

import javax.validation.constraints.NotBlank;
import java.beans.ConstructorProperties;

public final class ToDoCreateDto {
    @NotBlank(message = "Title must not be empty")
    private final String title;

    @ConstructorProperties({"title"})
    public ToDoCreateDto(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

}
