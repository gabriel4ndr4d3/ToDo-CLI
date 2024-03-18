package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskDataSource {

    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    public void add(Task task) {
        try {

            String json = gson.toJson(task);

            File dir = Util.getGlobalDir();

            if (!dir.exists() && !dir.mkdirs()) {
                throw new IOException("Failed to create global dir");
            }

            File file = new File(dir, task.getTitle());

            if (file.exists()) {
                throw new IllegalArgumentException("This task already exists");
            }

            if (!file.createNewFile()) {
                throw new IOException("Task creation failed");
            }

            Util.write(file, json);

        } catch (Exception e) {
            System.err.print(e.getMessage());
        }
    }

    public List<Task> list() {

        ArrayList<Task> tasks = new ArrayList<>();

        File diretorio = Util.getGlobalDir();

        File[] arquivos = diretorio.listFiles();

        if (arquivos != null && arquivos.length != 0) {

            for (File arquivo : arquivos) {

                String content = Util.read(arquivo);

                Task task = gson.fromJson(content, Task.class);

                tasks.add(task);
            }
        }

        return tasks;
    }
    public Task getTask(String taskName) {

        File directory = Util.getGlobalDir();
        File taskFile = new File(directory, taskName);

        String json = Util.read(taskFile);

        Task task = new Gson().fromJson(json, Task.class);

        return task;
    }
}
