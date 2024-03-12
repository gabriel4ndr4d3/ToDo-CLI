package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;

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
}
