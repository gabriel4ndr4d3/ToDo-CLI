package org.example;

import java.io.*;
import java.nio.file.Files;

public class Util {

    public static File getGlobalDir() {

        String userHome = System.getProperty("user.home");

        return new File(userHome, "todo");
    }

    public static void write(File file, String text) {

        try {
            OutputStream outputStream = Files.newOutputStream(file.toPath());

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);

            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

            bufferedWriter.write(text);

            bufferedWriter.close();

        } catch (IOException e) {
            System.err.print(e.getMessage());
        }
    }
}
