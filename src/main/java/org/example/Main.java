package org.example;

import com.google.gson.Gson;

import java.io.File;
import java.util.*;

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

    public void list() {

        File diretorio = Util.getGlobalDir();

        File[] arquivos = diretorio.listFiles();

        if (arquivos != null && arquivos.length != 0) {

            for (File arquivo : arquivos) {

                String content = Util.read(arquivo);

                Gson gson = new Gson();

                Task task = gson.fromJson(content, Task.class);

                System.out.println(task);
            }

        } else {
            System.out.println("Empty directory files");
        }
    }
}