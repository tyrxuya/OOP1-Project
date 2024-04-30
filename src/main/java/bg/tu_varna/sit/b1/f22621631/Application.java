package main.java.bg.tu_varna.sit.b1.f22621631;

import main.java.bg.tu_varna.sit.b1.f22621631.commands.CommandController;

import java.io.IOException;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) throws IOException {
        System.out.println("Library information system [Version 1.0]");
        System.out.println("(c) Vankata 22621631. All rights reserved");
        System.out.println();
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.print(">");
            String input = scanner.nextLine();
            System.out.println();
            CommandController.run(input);
            System.out.print("\n");
        } while(true);
    }
}
