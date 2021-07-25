package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DbServiceTestSuite {

    @Mock
    TaskRepository taskRepository;

    @InjectMocks
    DbService dbService;

    @Test
    void shouldGetAllTasks() {

        //GIVEN
        Task task1 = new Task(1L, "Test Title 1", "Test Content 1 ");
        Task task2 = new Task(2L, "Test Title 2", "Test Content 2 ");
        Task task3 = new Task(3L, "Test Title 3", "Test Content 3 ");
        ArrayList<Task> testTaskList = new ArrayList<>();
        testTaskList.add(task1);
        testTaskList.add(task2);
        testTaskList.add(task3);

        when(taskRepository.findAll()).thenReturn(testTaskList);

        //WHEN

        List<Task> allTasksReturnedByDbService = dbService.getAllTasks();

        //THEN

        Assertions.assertFalse(allTasksReturnedByDbService.isEmpty());
        Assertions.assertEquals(3, allTasksReturnedByDbService.size());

    }


    @Test
    void shouldDeleteTask() {

        //GIVEN

        //WHEN
        taskRepository.deleteById(1L);

        //THEN
        //Mockito.verify??
        verify(taskRepository, times(1)).deleteById(1L);
    }


    @Test
    void shouldGetTask() {

        //GIVEN
        Task task1 = new Task(1L, "Test Title 1", "Test Content 1 ");
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task1));

        //WHEN
        Optional<Task> expected = taskRepository.findById(1L);

        //THEN
        Assertions.assertTrue(expected.isPresent());

    }


    @Test
    void shouldSaveTask() {

        //GIVEN
        Task task1 = new Task(1L, "Test Title 1", "Test Content 1 ");
        when(taskRepository.save(task1)).thenReturn(task1);

        //WHEN
        Task taskSavedInDB = dbService.saveTask(task1);

        //THEN

        Assertions.assertEquals(task1.getId(), taskSavedInDB.getId());
        Assertions.assertEquals(task1.getContent(), taskSavedInDB.getContent());
        Assertions.assertEquals(task1.getTitle(), taskSavedInDB.getTitle());

    }

    @Test
    void shouldGetTaskWhichDoesNotExist(){

        //WHEN
        when(taskRepository.findById(1L)).thenReturn(Optional.empty());

        //WHEN
        Optional<Task> expected = taskRepository.findById(1L);

        //THEN
        Assertions.assertEquals(Optional.empty(),expected);

    }

}
