package main.java.bg.tu_varna.sit.b1.f22621631;

import main.java.bg.tu_varna.sit.b1.f22621631.commands.CommandController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) throws IOException {
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.print(">");
            CommandController.run(scanner.nextLine());
            System.out.print("\n\n");
        } while(true);
    }
}
