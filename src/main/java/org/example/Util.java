package org.example;

import java.io.*;

public class Util {

    public static File getGlobalFile() {

        String userHome = System.getProperty("user.home");

        return new File(userHome, "todo");
    }

    public static void write(File file, String text) {

        try {
            OutputStream outputStream = new FileOutputStream(file);

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);

            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

            bufferedWriter.write(text);

            bufferedWriter.close();

        } catch (IOException e) {
            System.err.print(e.getMessage());
        }
    }
}
