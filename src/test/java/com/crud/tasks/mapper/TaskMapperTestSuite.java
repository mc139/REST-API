package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class TaskMapperTestSuite {

    @Test
    void shouldMapToTask() {
        //GIVEN
        TaskMapper taskMapper = new TaskMapper();
        TaskDto taskDto = new TaskDto(1L, "test title", "test content");

        //WHEN
        Task task = taskMapper.mapToTask(taskDto);

        //THEN
        assertEquals(1L, task.getId());
        assertEquals("test content", task.getContent());
        assertEquals("test title", task.getTitle());
    }


    @Test
    void shouldMapToTaskDto() {

        //GIVEN
        TaskMapper taskMapper = new TaskMapper();
        Task task = new Task(2L, "test title", "test content");

        //WHEN
        TaskDto taskDto = taskMapper.mapToTaskDto(task);

        //THEN
        assertEquals(2L, task.getId());
        assertEquals("test content", task.getContent());
        assertEquals("test title", task.getTitle());

    }

    @Test
    void shouldMapToTaskListDto() {
        // GIVEN
        TaskMapper taskMapper = new TaskMapper();
        List<Task> taskList = new ArrayList<>();
        Task task1 = new Task(2L, "test title 1", "test content 1");
        Task task2 = new Task(3L, "test title 2", "test content 2");
        Task task3 = new Task(4L, "test title 3", "test content 3");

        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);

        // WHEN
        List<TaskDto> taskDtoList = taskMapper.mapToTaskDtoList(taskList);
        TaskDto taskDtoExpectedInTheList = taskDtoList.get(1);

        //THEN
        assertEquals(3, taskDtoList.size());
        assertTrue(taskDtoList.contains(taskDtoExpectedInTheList));

    }
}
