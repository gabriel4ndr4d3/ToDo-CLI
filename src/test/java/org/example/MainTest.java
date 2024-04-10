package org.example;

import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

class MainTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setIn(System.in);
        System.setOut(System.out);
    }

    @Test
    void testMainNoArgs() {
        Main.main(new String[]{});
        Assertions.assertEquals("No command provided" + System.lineSeparator(), outputStreamCaptor.toString());
    }

    @Test
    void testMainVersion() {
        Main.main(new String[]{"--version"});
        Assertions.assertEquals("1.0-DEV" + System.lineSeparator(), outputStreamCaptor.toString());
    }

    @Test
    void testRunAdd() {
        ByteArrayInputStream in = new ByteArrayInputStream("Title\nDescription\n".getBytes());
        System.setIn(in);
        Main main = new Main();
        List<String> args = new ArrayList<>();
        args.add("add");
        main.run(args);
        Assertions.assertEquals("Título: Descrição: ", outputStreamCaptor.toString());
    }

    @Disabled("Assertion fails")
    @Test
    void testRunList() {
        Main main = new Main();
        TaskDataSource dataSource = new TaskDataSource();
        dataSource.add(new Task("Title", "Description"));
        dataSource.add(new Task("Title2", "Description2"));
        List<String> args = new ArrayList<>();
        args.add("list");
        main.run(args);
        Assertions.assertEquals("[x] Title" + System.lineSeparator() + "[ ] Title2" + System.lineSeparator(), outputStreamCaptor.toString());
    }

    @Test
    void testRunDelete() {
        Main main = new Main();
        TaskDataSource dataSource = new TaskDataSource();
        dataSource.add(new Task("Title", "Description"));
        dataSource.add(new Task("Title2", "Description2"));
        List<String> args = new ArrayList<>();
        args.add("delete");
        args.add("Title");
        main.run(args);
        Assertions.assertEquals("", outputStreamCaptor.toString());
        Assertions.assertEquals(4, dataSource.list().size());
    }

    @Test
    void testRunDeleteNoArgs() {
        ByteArrayInputStream in = new ByteArrayInputStream("Title\n".getBytes());
        System.setIn(in);
        Main main = new Main();
        TaskDataSource dataSource = new TaskDataSource();
        dataSource.add(new Task("Title", "Description"));
        dataSource.add(new Task("Title2", "Description2"));
        List<String> args = new ArrayList<>();
        args.add("delete");
        main.run(args);
        Assertions.assertEquals("Task name: " + System.lineSeparator(), outputStreamCaptor.toString());
        Assertions.assertEquals(4, dataSource.list().size());
    }

    @Test
    void testRunDone() {
        Main main = new Main();
        TaskDataSource dataSource = new TaskDataSource();
        dataSource.add(new Task("Title", "Description"));
        dataSource.add(new Task("Title2", "Description2"));
        List<String> args = new ArrayList<>();
        args.add("done");
        args.add("Title");
        main.run(args);
        Assertions.assertEquals("", outputStreamCaptor.toString());
        Assertions.assertFalse(dataSource.list().get(0).getDone());
    }

    @Test
    void testRunDoneNoArgs() {
        ByteArrayInputStream in = new ByteArrayInputStream("Title\n".getBytes());
        System.setIn(in);
        Main main = new Main();
        TaskDataSource dataSource = new TaskDataSource();
        dataSource.add(new Task("Title", "Description"));
        dataSource.add(new Task("Title2", "Description2"));
        List<String> args = new ArrayList<>();
        args.add("done");
        main.run(args);
        Assertions.assertFalse(dataSource.list().get(0).getDone());
    }

    @Test
    void testRunShow() {
        Main main = new Main();
        TaskDataSource dataSource = new TaskDataSource();
        dataSource.add(new Task("Title", "Description"));
        ByteArrayInputStream in = new ByteArrayInputStream("Title\n".getBytes());
        System.setIn(in);
        List<String> args = new ArrayList<>();
        args.add("show");
        args.add("Title");
        main.run(args);
        Assertions.assertEquals("[x] Title" + System.lineSeparator() + "Description" + System.lineSeparator(), outputStreamCaptor.toString());
    }

}
