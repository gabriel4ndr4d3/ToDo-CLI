package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class TaskDataSourceTest {

    private final TaskDataSource taskDataSource = new TaskDataSource();

    @Test
    void testAddTask() {
        Task task = new Task("Test Task", "Description");
        taskDataSource.add(task);
        List<Task> tasks = taskDataSource.list();
        Assertions.assertEquals(5, tasks.size());
    }

    @Test
    void testListTasks() {
        Task task1 = new Task("Task 1", "Description 1");
        Task task2 = new Task("Task 2", "Description 2");
        taskDataSource.add(task1);
        taskDataSource.add(task2);
        List<Task> tasks = taskDataSource.list();
        Assertions.assertEquals(5, tasks.size());
        Assertions.assertTrue(tasks.stream().anyMatch(task -> task.getTitle().equals("Task 1")));
        Assertions.assertTrue(tasks.stream().anyMatch(task -> task.getTitle().equals("Task 2")));
    }

    @Test
    void testGetTask() {
        Task task = new Task("Test Task", "Description");
        taskDataSource.add(task);
        Task retrievedTask = taskDataSource.getTask("Test Task");
        Assertions.assertEquals(task.getTitle(), retrievedTask.getTitle());
        Assertions.assertEquals(task.getDescription(), retrievedTask.getDescription());
    }

    @Test
    void testDoneTask() {
        Task task = new Task("Test Task", "Description");
        taskDataSource.add(task);
        taskDataSource.done("Test Task");
        Task doneTask = taskDataSource.getTask("Test Task");
        Assertions.assertTrue(doneTask.getDone());
    }

    @Test
    void testDeleteTask() {
        Task task = new Task("Test Task", "Description");
        taskDataSource.add(task);
        taskDataSource.delete("Test Task");
        List<Task> tasks = taskDataSource.list();
        Assertions.assertEquals(4, tasks.size());
    }

}
