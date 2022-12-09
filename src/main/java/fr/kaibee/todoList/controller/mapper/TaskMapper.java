package fr.kaibee.todoList.controller.mapper;

import fr.kaibee.todoList.controller.dto.TaskCreateDto;
import fr.kaibee.todoList.controller.dto.TaskDto;
import fr.kaibee.todoList.domain.model.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    Task mapTo(TaskCreateDto taskToCreateDto);

    @Mapping(target = "status", source = "status")
    TaskDto mapTo(Task task);

    @Mapping(target = "status", source = "status")
    Task mapTo(TaskDto task);
}
