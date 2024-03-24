package main.java.bg.tu_varna.sit.b1.f22621631;

import main.java.bg.tu_varna.sit.b1.f22621631.commands.CommandController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Application {
    public static void main(String[] args) throws IOException {
        do {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(System.in)
            );
            System.out.print(">");
            CommandController.run(reader.readLine());
            System.out.print("\n\n");
        } while(true);
    }
}
