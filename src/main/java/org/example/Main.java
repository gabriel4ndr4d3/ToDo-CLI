package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    private final Scanner scanner = new Scanner(System.in);
    private final TaskDataSource dataSource = new TaskDataSource();

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("No command provided");
            return;
        }

        Main main = new Main();

        main.run(new ArrayList<>(Arrays.asList(args)));
    }

    public void run(List<String> args) {

        String command = args.get(0);

        if (command.equals("--version")) {
            System.out.println("1.0-DEV");
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

                System.out.println("Task name: ");

                String taskName = scanner.nextLine();

                delete(taskName);
            } else {
                delete(args.get(0));
            }

        }

        if (command.equals("show")) {

            args.remove(0);

            if (args.isEmpty()) {

                System.out.print("Task name: ");

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

        if (command.equals("--help")) {
            args.remove(0);
            help();
        }
    }

    private void add() {
        try {
            System.out.print("Título: ");
            String title = scanner.nextLine();

            System.out.print("Descrição: ");
            String description = scanner.nextLine();

            Task task = new Task(title, description);

            dataSource.add(task);
        } catch (Exception e) {
            System.err.print(e.getMessage());
        }
    }

    public void list() {

        List<Task> tasks = dataSource.list();

        if (tasks.isEmpty()) {

            System.out.println("Empty directory files!!");

            return;
        }

        for (Task task : tasks) {
            System.out.println(task);
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

        System.out.println(task);
        System.out.println(task.getDescription());
    }

    private void help() {
        System.out.println("--version\tShows the version of the application");
        System.out.println("add\t\t\tIt allows a new task to be created");
        System.out.println("list\t\tAll tasks are shown in a list");
        System.out.println("delete\t\tIt allows a task to be deleted");
        System.out.println("show\t\tA specific task's information is provided");
        System.out.println("done\t\tA specific task can be marked as done");
    }

}