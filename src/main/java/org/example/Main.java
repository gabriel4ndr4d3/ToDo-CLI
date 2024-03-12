package org.example;

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
        if (command.equals("delete")) {

            args.removeFirst();

            delete();

        }

        scanner.close();
    }

    public void add() {
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
    public void delete() {

        File directory = Util.getGlobalFile();

        System.out.println("File name to delete: ");
        String nameToDelete = scanner.nextLine();

        File fileToDelete = new File(directory, nameToDelete);

        if (!fileToDelete.exists()) {
            System.err.println("This file don't exist");
            return;
        }

        if (!fileToDelete.delete()) {
            System.err.println("Can't delete");
        }
    }
}