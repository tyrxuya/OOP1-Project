package main.java.bg.tu_varna.sit.b1.f22621631.commands.user;

import main.java.bg.tu_varna.sit.b1.f22621631.commands.utility.data.AppData;
import main.java.bg.tu_varna.sit.b1.f22621631.contracts.controllers.RunnableCommand;
import main.java.bg.tu_varna.sit.b1.f22621631.lists.UserList;
import main.java.bg.tu_varna.sit.b1.f22621631.users.User;

import java.io.Console;
import java.io.FileNotFoundException;
import java.util.Arrays;

public class Login implements RunnableCommand {
    @Override
    public void execute() throws Exception {
        if (AppData.getInstance().getOpenedFile() == null) {
            throw new FileNotFoundException("Cannot perform user operations without opening the file!");
        }

        if (AppData.getInstance().getOpenedFile().getName().equals("books.xml")) {
            throw new Exception("Cannot perform user operations while working on books file!");
        }

        if (AppData.getInstance().getActiveUser() != null) {
            throw new Exception("Already logged in!");
        }

        Console console = System.console();

        String username = console.readLine("Enter username: ");

        char[] password = console.readPassword("Enter password: ");
        StringBuilder passwordBuilder = new StringBuilder();
        for (Character passwordChar : password) {
            passwordBuilder.append(passwordChar);
            System.out.print("*");
        }
        System.out.println();
        String passwordText = passwordBuilder.toString();

        User attemptedUser = UserList.getInstance().findUser(username);
        if (!attemptedUser.getPassword().equals(passwordText)) {
            throw new Exception("Wrong credentials!");
        }
        AppData.getInstance().setActiveUser(attemptedUser);
        System.out.println("Welcome " + username + "!");
    }
}
