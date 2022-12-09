package fr.kaibee.todoList.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ToDo {
    private List<Task> tasks;
    private UUID id;
    private String title;

    public ToDo(String title) {
        this.title = title;
        this.tasks = new ArrayList<>();
    }
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    public void addTask(Task task) {
        task.setToDo(this);
        tasks.add(task);
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
