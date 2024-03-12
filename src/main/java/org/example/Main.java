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

        String command = args.getFirst();

        if (command.equals("--version")) {
            System.out.println("1.0-DEV");
            return;
        }

        if (command.equals("add")) {

            args.removeFirst();

            add();

            return;
        }
    }

    public void add() {

        System.out.print("Título: ");
        String title = scanner.nextLine();

        System.out.print("Descrição: ");
        String description = scanner.nextLine();

        Task task = new Task(title, description);

        dataSource.add(task);
    }
}