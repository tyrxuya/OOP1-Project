package main.java.bg.tu_varna.sit.b1.f22621631.commands.user;

import main.java.bg.tu_varna.sit.b1.f22621631.commands.utility.data.AppData;
import main.java.bg.tu_varna.sit.b1.f22621631.contracts.controllers.RunnableCommand;
import main.java.bg.tu_varna.sit.b1.f22621631.lists.UserList;
import main.java.bg.tu_varna.sit.b1.f22621631.users.PermissionLevel;
import main.java.bg.tu_varna.sit.b1.f22621631.users.User;

import java.io.Console;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AddUser implements RunnableCommand {
    private List<String> argument;

    public AddUser(List<String> argument) {
        this.argument = argument;
    }

    @Override
    public void execute() throws Exception {
        if (AppData.getInstance().getOpenedFile() == null) {
            throw new FileNotFoundException("Cannot perform user operations without opening the file!");
        }

        if (AppData.getInstance().getOpenedFile().getName().equals("books.xml")) {
            throw new Exception("Cannot perform user operations while working on books file!");
        }

        if (AppData.getInstance().getActiveUser() == null) {
            throw new Exception("Cannot add user without being logged in!");
        }

        if (AppData.getInstance().getActiveUser().getPermissionLevel().getText().equals("User")) {
            throw new Exception("Access denied, ADMINISTRATOR permission required!");
        }

        if (UserList.getInstance().userExists(argument.get(0))) {
            throw new Exception("User with such username already exists!");
        }

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter permission level (admin or user): ");
        String permissionLevelText = scanner.nextLine().toLowerCase();
        PermissionLevel permissionLevel = PermissionLevel.valueOf(permissionLevelText.equals("user") ? permissionLevelText.toUpperCase() : "ADMINISTRATOR");

        UserList.getInstance().add(new User(argument.get(0), argument.get(1), permissionLevel));
    }
}
