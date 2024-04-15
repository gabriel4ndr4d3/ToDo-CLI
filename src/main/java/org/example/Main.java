package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    private final Scanner scanner = new Scanner(System.in);
    private final TaskDataSource dataSource = new TaskDataSource();

    public static void main(String[] args) {

        if (args.length == 0) {
            logger.error("No command provided {}", System.lineSeparator());
            return;
        }

        Main main = new Main();

        main.run(new ArrayList<>(Arrays.asList(args)));
    }

    public void run(List<String> args) {

        String command = args.get(0);

        if (command.equals("--version")) {
            logger.info("1.0-DEV {}", System.lineSeparator());
            return;
        }

        if (command.equals("add")) {

            args.remove(0);

            add();

            return;
        }
        if (command.equals("list")) {

            args.remove(0);

            list();
        }

        if (command.equals("delete")) {

            args.remove(0);

            if (args.isEmpty()) {

                logger.info("Task name: {}", System.lineSeparator());

                String taskName = scanner.nextLine();

                delete(taskName);
            } else {
                delete(args.get(0));
            }

        }

        if (command.equals("show")) {

            args.remove(0);

            if (args.isEmpty()) {

                logger.info("Task name: ");

                String taskName = scanner.nextLine();

                show(taskName);
            } else {
                show(args.get(0));
            }
        }
        if (command.equals("done")) {

            args.remove(0);

            if (args.isEmpty()) {

                String taskName = scanner.nextLine();

                done(taskName);
            } else {
                done(args.get(0));
            }
        }
    }

    private void add() {
        try {
            logger.info("Título: ");
            String title = scanner.nextLine();

            logger.info("Descrição: ");
            String description = scanner.nextLine();

            Task task = new Task(title, description);

            dataSource.add(task);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void list() {

        List<Task> tasks = dataSource.list();

        if (tasks.isEmpty()) {

            logger.warn("Empty directory files!! {}", System.lineSeparator());

            return;
        }

        for (Task task : tasks) {
            logger.info("{}", task);
            logger.info(System.lineSeparator());
        }
    }

    private void delete(String nameToDelete) {
        dataSource.delete(nameToDelete);
    }

    public void done(String fileName) {
        dataSource.done(fileName);
    }

    public void show(String taskName) {

        Task task = dataSource.getTask(taskName);

        logger.info("{}", task);
        logger.info(System.lineSeparator());
        logger.info("{}", task.getDescription());
        logger.info(System.lineSeparator());
    }

}