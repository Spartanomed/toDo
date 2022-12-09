package fr.kaibee.todoList.repository.internal;

import fr.kaibee.todoList.domain.exception.ElementNotFoundException;
import fr.kaibee.todoList.domain.exception.ExceptionMessage;
import fr.kaibee.todoList.domain.model.Task;
import fr.kaibee.todoList.domain.model.ToDo;
import fr.kaibee.todoList.repository.TaskRepository;
import fr.kaibee.todoList.repository.ToDoRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ToDoRepositoryImpl implements ToDoRepository {
    private final TaskRepository taskRepository;
    private final Map<UUID, ToDo> toDos = new ConcurrentHashMap<>();

    public ToDoRepositoryImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public void save(ToDo toDo) {
        toDo.setId(UUID.randomUUID());
        toDos.put(toDo.getId(), toDo);
    }

    @Override
    public ToDo findById(UUID id) {
        ToDo toDo = toDos.get(id);
        if(Objects.isNull(toDo)) {
            throw new ElementNotFoundException(String.format(ExceptionMessage.NO_SUCH_ELEMENT, id));
        }
        toDo.setTasks(taskRepository.findByToDoId(id));
        return toDo;
    }

    @Override
    public void addTask(UUID id, Task task) {
        ToDo toDo = findById(id);
        toDo.addTask(task);
        taskRepository.save(task);
    }

    @Override
    public List<ToDo> findAll() {
        for (ToDo toDo : toDos.values()) {
            toDo.setTasks(taskRepository.findByToDoId(toDo.getId()));
        }
        return new ArrayList<>(toDos.values());
    }
}
