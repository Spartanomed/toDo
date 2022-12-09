package fr.kaibee.todoList.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.kaibee.todoList.controller.dto.ToDoCreateDto;
import fr.kaibee.todoList.controller.dto.ToDoDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
class ToDoControllerIT {
    @Autowired
    MockMvc mockMvc;

    @Test
    public void Should_Create_ToDo_When_Create_Is_Call() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/todo")
                        .content(new ObjectMapper().writeValueAsString(new ToDoCreateDto("MyToDoList")))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect((MockMvcResultMatchers.jsonPath("$.title").value("MyToDoList")))
                .andExpect((MockMvcResultMatchers.jsonPath("$.id").isNotEmpty()));
    }

    @Test
    public void Should_Find_ToDo_When_Created() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/todo")
                        .content(new ObjectMapper().writeValueAsString(new ToDoCreateDto("MyToDoList")))
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        ToDoDto toDoDto = new ObjectMapper().readValue(
                result.getResponse().getContentAsString(),
                ToDoDto.class);

        mockMvc.perform(MockMvcRequestBuilders.get("/todo/{id}", toDoDto.getId()))
                .andExpect(status().isOk())
                .andExpect((MockMvcResultMatchers.jsonPath("$.id")).value(toDoDto.getId().toString()))
                .andExpect((MockMvcResultMatchers.jsonPath("$.title").value(toDoDto.getTitle())))
                .andExpect((MockMvcResultMatchers.jsonPath("$.tasks").value(toDoDto.getTasks())));
    }

    @Test
    public void Should_Not_Found_ToDo_When_Id_Not_Exist() throws Exception {
        UUID idNotFound = UUID.randomUUID();
        mockMvc.perform(MockMvcRequestBuilders.get("/todo/{id}", idNotFound.toString()))
                .andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value("No such element with id : " + idNotFound));
    }

    @Test
    public void Should_Return_All_ToDo_When_Find_All_Is_Call() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/todo")
                .content(new ObjectMapper().writeValueAsString(new ToDoCreateDto("MyToDoList1")))
                .contentType(MediaType.APPLICATION_JSON));
        mockMvc.perform(MockMvcRequestBuilders.post("/todo")
                .content(new ObjectMapper().writeValueAsString(new ToDoCreateDto("MyToDoList2")))
                .contentType(MediaType.APPLICATION_JSON));

        mockMvc.perform(MockMvcRequestBuilders.get("/todo"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.toDos", hasSize(2)));
    }

}