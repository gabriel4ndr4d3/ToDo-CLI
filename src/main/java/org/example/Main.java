package org.example;

public class Main {
    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("No command provided");
            return;
        }

        String command = args[0];

        if(command.equals("--version")) {
            System.out.println("1.0-DEV");
            return;
        }
    }
}