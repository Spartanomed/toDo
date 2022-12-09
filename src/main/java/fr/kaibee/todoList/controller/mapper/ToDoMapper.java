package fr.kaibee.todoList.controller.mapper;

import fr.kaibee.todoList.controller.dto.ToDoCreateDto;
import fr.kaibee.todoList.controller.dto.ToDoDto;
import fr.kaibee.todoList.domain.model.ToDo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = TaskMapper.class)
public interface ToDoMapper {
    ToDoDto mapTo(ToDo toDo);

    ToDo mapTo(ToDoCreateDto toDoToCreateDto);

    List<ToDoDto> mapTo(List<ToDo> toDos);
}
