package fr.kaibee.todoList.repository;

import fr.kaibee.todoList.domain.exception.ElementNotFoundException;
import fr.kaibee.todoList.domain.exception.ExceptionMessage;
import fr.kaibee.todoList.domain.model.StatusTask;
import fr.kaibee.todoList.domain.model.Task;
import fr.kaibee.todoList.domain.model.ToDo;
import fr.kaibee.todoList.repository.internal.TaskRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

class TaskRepositoryImplTest {
    @Test
    public void Should_Generate_Id_When_Task_Is_Save() {
        TaskRepository taskRepository = new TaskRepositoryImpl();
        Task taskToCreate = new Task("MyLabel");

        taskRepository.save(taskToCreate);

        Assertions.assertNotNull(taskToCreate.getId());
    }

    @Test
    public void Should_Find_By_Id_When_Task_Already_Save() {
        TaskRepository taskRepository = new TaskRepositoryImpl();
        Task taskToCreate = new Task("MyLabel");
        taskRepository.save(taskToCreate);

        Task taskFind = taskRepository.findTaskById(taskToCreate.getId());

        Assertions.assertEquals(taskToCreate, taskFind);
    }

    @Test
    public void Should_Throw_ElementNotFoundException_When_Find_By_Id_NotFound() {
        TaskRepository taskRepository = new TaskRepositoryImpl();
        UUID idNotExisting = UUID.randomUUID();

        ElementNotFoundException exception = Assertions.assertThrows(ElementNotFoundException.class,
                () -> taskRepository.findTaskById(idNotExisting));

        Assertions.assertEquals(String.format(ExceptionMessage.NO_SUCH_ELEMENT, idNotExisting), exception.getMessage());
    }

    @Test
    public void Should_Update_Status_When_Update_Call() {
        TaskRepository taskRepository = new TaskRepositoryImpl();
        Task taskToCreate = new Task("MyLabel");
        taskRepository.save(taskToCreate);
        taskToCreate.setStatus(StatusTask.COMPLETE);

        taskRepository.update(taskToCreate.getId(), taskToCreate);

        Assertions.assertEquals(StatusTask.COMPLETE, taskToCreate.getStatus());
    }

    @Test
    public void Should_Throw_ElementNotFoundException_When_Update_Task_Not_Exist() {
        TaskRepository taskRepository = new TaskRepositoryImpl();
        Task taskToCreate = new Task("MyLabel");
        taskToCreate.setStatus(StatusTask.COMPLETE);
        UUID idNotExist = UUID.randomUUID();

        ElementNotFoundException exception = Assertions.assertThrows(ElementNotFoundException.class,
                () -> taskRepository.update(idNotExist, taskToCreate));

        Assertions.assertEquals(String.format(ExceptionMessage.NO_SUCH_ELEMENT, idNotExist),
                exception.getMessage());
    }

    @Test
    public void Should_Return_All_Task_For_ToDo_When_ToDo_Have_Multi_Task() {
        TaskRepository taskRepository = new TaskRepositoryImpl();
        ToDo toDo = new ToDo("MyToDoList");
        toDo.setId(UUID.randomUUID());
        Task taskToCreate1 = new Task("MyLabel1");
        toDo.addTask(taskToCreate1);
        Task taskToCreate2 = new Task("MyLabel2");
        toDo.addTask(taskToCreate2);
        taskRepository.save(taskToCreate1);
        taskRepository.save(taskToCreate2);

        List<Task> tasksResult = taskRepository.findByToDoId(toDo.getId());

        Assertions.assertEquals(tasksResult.size(), 2);
        Assertions.assertTrue(tasksResult.stream().anyMatch(taskToCreate1::equals));
        Assertions.assertTrue(tasksResult.stream().anyMatch(taskToCreate2::equals));
    }
}