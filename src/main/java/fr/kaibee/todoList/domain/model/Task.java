package fr.kaibee.todoList.domain.model;

import java.util.UUID;

public class Task {
    private UUID id;
    private String label;

    private StatusTask status;

    private ToDo toDo;

    public Task(String label) {
        this.label = label;
        this.status = StatusTask.IN_PROGRESS;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public ToDo getToDo() {
        return toDo;
    }

    public void setToDo(ToDo toDo) {
        this.toDo = toDo;
    }

    public StatusTask getStatus() {
        return status;
    }

    public void setStatus(StatusTask status) {
        this.status = status;
    }
}
