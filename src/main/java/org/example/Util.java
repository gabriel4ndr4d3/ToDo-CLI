package org.example;

import java.io.*;
import java.nio.file.Files;
import java.util.Scanner;

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

    public static String read(File dir) {

        StringBuilder stringBuilder = new StringBuilder();

        try {

            InputStream inputStream = Files.newInputStream(dir.toPath());
            InputStreamReader streamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(streamReader);

            String line = bufferedReader.readLine();

            while (line != null) {
                stringBuilder.append(line);
                stringBuilder.append('\n');
                line = bufferedReader.readLine();
            }

            bufferedReader.close();

        } catch (IOException e) {
            System.err.print(e.getMessage());
        }

        return stringBuilder.toString();
    }
}

