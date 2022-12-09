package fr.kaibee.todoList.configuration;

import fr.kaibee.todoList.domain.service.TaskService;
import fr.kaibee.todoList.domain.service.ToDoService;
import fr.kaibee.todoList.repository.TaskRepository;
import fr.kaibee.todoList.repository.ToDoRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {
    @Bean
    public TaskService taskService(TaskRepository taskRepository) {
        return new TaskService(taskRepository);
    }

    @Bean
    public ToDoService toDoService(ToDoRepository toDoRepository) {
        return new ToDoService(toDoRepository);
    }
}
