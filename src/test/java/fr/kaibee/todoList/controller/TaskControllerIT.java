package fr.kaibee.todoList.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.kaibee.todoList.controller.dto.TaskCreateDto;
import fr.kaibee.todoList.controller.dto.TaskDto;
import fr.kaibee.todoList.controller.dto.ToDoCreateDto;
import fr.kaibee.todoList.controller.dto.ToDoDto;
import fr.kaibee.todoList.domain.model.StatusTask;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TaskControllerIT {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    TaskController taskController;
    @Autowired
    ToDoController toDoController;

    @Test
    public void Should_Update_Task_When_Update_Is_Call() throws Exception {
        ToDoDto toDoDto = toDoController.create(new ToDoCreateDto("MyToDo"));
        TaskDto taskSave = taskController.create(toDoDto.getId(),
                new TaskCreateDto("oldLabel"));
        TaskDto taskToUpdate = new TaskDto(taskSave.getId(), "label", StatusTask.COMPLETE.name());

        mockMvc.perform(MockMvcRequestBuilders.put("/todo/{idTodo}/task/{id}", toDoDto.getId(), taskToUpdate.getId())
                        .content(new ObjectMapper().writeValueAsString(taskToUpdate))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((MockMvcResultMatchers.jsonPath("$.label").value("oldLabel")))
                .andExpect((MockMvcResultMatchers.jsonPath("$.status").value(StatusTask.COMPLETE.name())));
    }

    @Test
    public void Should_Create_Task_When_Add_Is_Call() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/todo")
                        .content(new ObjectMapper().writeValueAsString(new ToDoCreateDto("MyToDoList")))
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        ToDoDto toDoDto = new ObjectMapper().readValue(
                result.getResponse().getContentAsString(),
                ToDoDto.class);

        mockMvc.perform(MockMvcRequestBuilders.post("/todo/{id}/task", toDoDto.getId())
                        .content(new ObjectMapper().writeValueAsString(new TaskCreateDto("MyLabel")))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.label").value("MyLabel"));
    }

    @Test
    public void Should_Throw_NotFound_When_Add_Is_Call_On_ToDO_Not_Existing() throws Exception {
        UUID idNotFound = UUID.randomUUID();
        mockMvc.perform(MockMvcRequestBuilders.post("/todo/{id}/task", idNotFound)
                        .content(new ObjectMapper().writeValueAsString(new TaskCreateDto("MyLabel")))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value("No such element with id : " + idNotFound));
    }
}
