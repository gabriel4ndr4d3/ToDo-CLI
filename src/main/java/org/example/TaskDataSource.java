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

            File dir = Util.getGlobalFile();

            if (!dir.exists() && !dir.mkdirs()) {
                throw new RuntimeException("Failed to create global dir");
            }

            File file = new File(dir, task.getTitle());

            if (!file.exists() && !file.createNewFile()) {
                throw new RuntimeException("Failed to create task file");
            }

            Util.write(file, json);

        } catch (IOException e) {
            System.err.print(e.getMessage());
        }
    }
}
