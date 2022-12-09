package fr.kaibee.todoList.repository.internal;

import fr.kaibee.todoList.domain.exception.ElementNotFoundException;
import fr.kaibee.todoList.domain.exception.ExceptionMessage;
import fr.kaibee.todoList.domain.model.Task;
import fr.kaibee.todoList.repository.TaskRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class TaskRepositoryImpl implements TaskRepository {
    private final Map<UUID, Task> tasks = new ConcurrentHashMap<>();
    @Override
    public void save(Task task) {
        task.setId(UUID.randomUUID());
        tasks.put(task.getId(), task);
    }

    @Override
    public List<Task> findByToDoId(UUID toDoId) {
        return tasks.values().stream()
                .filter(task -> toDoId.equals(task.getToDo().getId()))
                .collect(Collectors.toList());
    }

    @Override
    public Task findTaskById(UUID id) {
        if(!tasks.containsKey(id)) {
            throw new ElementNotFoundException(String.format(ExceptionMessage.NO_SUCH_ELEMENT, id));
        }
        return tasks.get(id);
    }

    @Override
    public void update(UUID id, Task taskToUpdate) {
        if(!tasks.containsKey(id)) {
            throw new ElementNotFoundException(String.format(ExceptionMessage.NO_SUCH_ELEMENT, id));
        }
        tasks.replace(id, taskToUpdate);
    }
}
