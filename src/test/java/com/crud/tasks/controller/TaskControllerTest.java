package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@SpringJUnitWebConfig
@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskMapper taskMapper;

    @MockBean
    DbService dbService;


    @Test
    void shouldReturnEmptyTaskList() throws Exception {

        //GIVEN
        List<TaskDto> taskDtoList = new ArrayList<>();
        when(taskMapper.mapToTaskDtoList(new ArrayList<>())).thenReturn(taskDtoList);


        //WHEN & THEN
        mockMvc.perform(MockMvcRequestBuilders
                .get("/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    void shouldReturnTaskList() throws Exception {

        //GIVEN
        List<TaskDto> taskDtoList = new ArrayList<>();
        taskDtoList.add(new TaskDto(1L, "Test", "Test content 1"));
        when(taskMapper.mapToTaskDtoList(anyList())).thenReturn(taskDtoList);

        //WHEN & THEN
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .param("taskId", "1"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title", Matchers.is("Test")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].content", Matchers.is("Test content 1")));
    }

    @Test
    void shouldCreateTask() throws Exception {

        Task task = new Task(1L, "Test", "Test content 1");
        TaskDto taskDto = new TaskDto(1L, "Test", "Test content 1");

        when(taskMapper.mapToTaskDto(ArgumentMatchers.any(Task.class))).thenReturn(taskDto);
        when(dbService.getTask(1L)).thenReturn(java.util.Optional.of(task));
        Gson gson = new Gson();
        String json = gson.toJson(taskDto);

        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().is(200));
        verify(taskMapper,times(1)).mapToTask(any());
        verify(dbService,times(1)).saveTask(any());


    }

    @Test
    void shouldDeleteTask() throws Exception {

        Task task = new Task(1L, "Test", "Test content 1");
        TaskDto taskDto = new TaskDto(1L, "Test", "Test content 1");

        when(taskMapper.mapToTaskDto(ArgumentMatchers.any(Task.class))).thenReturn(taskDto);
        when(dbService.getTask(1L)).thenReturn(java.util.Optional.of(task));

        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/v1/tasks/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8"))
                .andExpect(MockMvcResultMatchers.status().is(200));

    }

    @Test
    void shouldUpdateTask() throws Exception {

        Task task = new Task(1L, "Test", "Test content 1");
        TaskDto taskDto = new TaskDto(1L, "Test", "Test content 1");

        when(taskMapper.mapToTaskDto(ArgumentMatchers.any(Task.class))).thenReturn(taskDto);
        when(dbService.getTask(1L)).thenReturn(java.util.Optional.of(task));


        Gson gson = new Gson();
        String json = gson.toJson(taskDto);

        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/tasks/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
}