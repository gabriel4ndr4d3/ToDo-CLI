package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TaskTest {

    @Test
    void testTaskInitialization() {
        Task task = new Task("Title", "Description");
        Assertions.assertEquals("Title", task.getTitle());
        Assertions.assertEquals("Description", task.getDescription());
        Assertions.assertFalse(task.getDone());
    }

    @Test
    void testTaskInitializationWithTitleOnly() {
        Task task = new Task("Title", "");
        Assertions.assertEquals("Title", task.getTitle());
        Assertions.assertEquals("", task.getDescription());
        Assertions.assertFalse(task.getDone());
    }

    @Test
    void testTaskInitializationWithEmptyTitle() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Task("", "Description");
        });
    }

    @Test
    void testDoneTask() {
        Task task = new Task("Title", "Description");
        task.done();
        Assertions.assertTrue(task.getDone());
    }

    @Test
    void testToStringForIncompleteTask() {
        Task task = new Task("Title", "Description");
        Assertions.assertEquals("[ ] Title", task.toString());
    }

    @Test
    void testToStringForCompletedTask() {
        Task task = new Task("Title", "Description");
        task.done();
        Assertions.assertEquals("[x] Title", task.toString());
    }

}
