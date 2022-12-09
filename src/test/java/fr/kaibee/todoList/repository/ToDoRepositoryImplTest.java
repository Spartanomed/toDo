package fr.kaibee.todoList.repository;

import fr.kaibee.todoList.domain.exception.ElementNotFoundException;
import fr.kaibee.todoList.domain.exception.ExceptionMessage;
import fr.kaibee.todoList.domain.model.Task;
import fr.kaibee.todoList.domain.model.ToDo;
import fr.kaibee.todoList.repository.internal.ToDoRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class ToDoRepositoryImplTest {
    @InjectMocks
    ToDoRepositoryImpl toDoRepository;
    @Mock
    TaskRepository taskRepository;

    @Test
    public void Should_Generate_Id_When_Save() {
        ToDo toDoToCreate = new ToDo("New toDo");

        toDoRepository.save(toDoToCreate);

        Assertions.assertNotNull(toDoToCreate.getId());
    }


    @Test
    public void Should_Find_By_Id_When_ToDo_Already_Save() {
        ToDo toDoExpected = new ToDo("New toDo");
        toDoRepository.save(toDoExpected);

        ToDo toDoSearch = toDoRepository.findById(toDoExpected.getId());

        Assertions.assertEquals(toDoExpected, toDoSearch);
    }

    @Test
    public void Should_Throw_ElementNotFoundException_When_Find_Return_null() {
        UUID idNotExist = UUID.randomUUID();

        ElementNotFoundException expectedException = Assertions.assertThrows(
                ElementNotFoundException.class,
                () -> toDoRepository.findById(idNotExist));

        Assertions.assertEquals(String.format(ExceptionMessage.NO_SUCH_ELEMENT, idNotExist),
                expectedException.getMessage());
    }

    @Test
    public void Should_Save_Task_On_ToDo_When_ToDo_Already_Save() {
        ToDo toDo = new ToDo("MyToDo");
        toDoRepository.save(toDo);
        Task taskToCreate = new Task("label");

        toDoRepository.addTask(toDo.getId(), taskToCreate);

        Assertions.assertEquals(toDo.getId(), taskToCreate.getToDo().getId());
    }
}
