package fr.kaibee.todoList.domain.service;

import fr.kaibee.todoList.domain.model.Task;
import fr.kaibee.todoList.domain.model.ToDo;
import fr.kaibee.todoList.repository.ToDoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class ToDoServiceTest {
    @InjectMocks
    ToDoService toDoService;
    @Mock
    ToDoRepository toDoRepository;

    @Test
    public void Should_Map_ToDoDto_And_Create_When_Create_Call() {
        ToDo toDoToCreate = new ToDo("New Dto");
        toDoToCreate.setId(UUID.randomUUID());

        ToDo toDoResult = toDoService.create(toDoToCreate);

        Assertions.assertEquals(toDoToCreate.getId(), toDoResult.getId());
    }

    @Test
    public void Should_Find_And_Map_ToDo_When_Create_Find_Id() {
        UUID idToFind = UUID.randomUUID();
        ToDo toDoFind = new ToDo("toDoFind");
        toDoFind.setId(idToFind);
        Mockito.when(toDoRepository.findById(idToFind)).thenReturn(toDoFind);

        ToDo toDoResult = toDoService.findById(idToFind);

        Assertions.assertEquals(toDoFind, toDoResult);
    }

    @Test
    public void Should_Map_And_Add_Task_When_ToDoId_Exist() {
        UUID toDoId = UUID.randomUUID();
        Task taskToCreate = new Task("MyLabel");
        taskToCreate.setId(UUID.randomUUID());

        Task taskCreated = toDoService.addTask(toDoId, taskToCreate);

        Assertions.assertEquals(taskToCreate, taskCreated);
    }
}