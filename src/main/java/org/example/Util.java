package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;

public class Util {

    private static final Logger logger = LoggerFactory.getLogger(Util.class);

    private Util() {
        // empty constructor
    }

    public static File getGlobalDir() {

        String userHome = System.getProperty("user.home");

        return new File(userHome, "todo");
    }

    public static void write(File file, String text) {

        try {
            OutputStream outputStream = Files.newOutputStream(file.toPath());

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);

            try (BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter)) {

                bufferedWriter.write(text);

            }

        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    public static String read(File dir) {

        StringBuilder stringBuilder = new StringBuilder();

        try {

            InputStream inputStream = Files.newInputStream(dir.toPath());
            InputStreamReader streamReader = new InputStreamReader(inputStream);
            try (BufferedReader bufferedReader = new BufferedReader(streamReader)) {

                String line = bufferedReader.readLine();

                while (line != null) {
                    stringBuilder.append(line);
                    stringBuilder.append('\n');
                    line = bufferedReader.readLine();
                }

            }

        } catch (IOException e) {
            logger.error(e.getMessage());
        }

        return stringBuilder.toString();
    }
}

