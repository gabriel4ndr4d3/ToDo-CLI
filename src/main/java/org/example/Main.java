package org.example;

import com.google.gson.Gson;

import java.io.File;
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
                delete(getNameToDelete());
            } else {
                delete(args.get(0));
            }

        }

        if (command.equals("show")) {

            args.remove(0);

            show();
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

        File directory = Util.getGlobalDir();

        File fileToDelete = new File(directory, nameToDelete);

        if (!fileToDelete.exists()) {
            System.err.println("This file don't exist");
            return;
        }

        if (!fileToDelete.delete()) {
            System.err.println("Can't delete");
        }
    }

    private String getNameToDelete() {
        System.out.println("File name to delete: ");
        return scanner.nextLine();
    }

    public void show() {

        String taskName = scanner.nextLine();

        File directory = Util.getGlobalDir();

        File taskFile = new File(directory, taskName);

        String json = Util.read(taskFile);

        Task task = new Gson().fromJson(json, Task.class);

        System.out.println(task);
        System.out.println(task.getDescription());
    }

}