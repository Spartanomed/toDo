package fr.kaibee.todoList.controller.dto;

import java.beans.ConstructorProperties;
import java.util.UUID;

public final class TaskDto {
    private final UUID id;
    private final String label;
    private final String status;

    @ConstructorProperties({"id", "label", "status"})
    public TaskDto(UUID id, String label, String status) {
        this.id = id;
        this.label = label;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public String getStatus() {
        return status;
    }
}
