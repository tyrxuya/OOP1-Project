package main.java.bg.tu_varna.sit.b1.f22621631.commands.user;

import main.java.bg.tu_varna.sit.b1.f22621631.commands.utility.data.AppData;
import main.java.bg.tu_varna.sit.b1.f22621631.contracts.controllers.RunnableCommand;
import main.java.bg.tu_varna.sit.b1.f22621631.exceptions.files.UserFileNotOpenedException;
import main.java.bg.tu_varna.sit.b1.f22621631.exceptions.files.WrongFileOpenedException;
import main.java.bg.tu_varna.sit.b1.f22621631.exceptions.models.users.InvalidPermissionLevelException;
import main.java.bg.tu_varna.sit.b1.f22621631.exceptions.models.users.InvalidUserException;
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
    public void execute() {
        if (AppData.getInstance().getOpenedFile() == null) {
            throw new UserFileNotOpenedException("Cannot perform user operations without opening the file!"); //UserFileNotOpenedException
        }

        if (AppData.getInstance().getOpenedFile().getName().equals("books.xml")) {
            throw new WrongFileOpenedException("Cannot perform user operations while working on books file!"); //WrongFileOpenedException
        }

        if (AppData.getInstance().getActiveUser() == null) {
            throw new InvalidUserException("Cannot add user without being logged in!"); //LoginException
        }

        if (AppData.getInstance().getActiveUser().getPermissionLevel().getText().equals("User")) {
            throw new InvalidPermissionLevelException("Access denied, ADMINISTRATOR permission required!"); //PermissionLevelException
        }

        if (UserList.getInstance().userExists(argument.get(0))) {
            throw new InvalidUserException("User with such username already exists!"); //LoginException
        }

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter permission level (admin or user): ");
        String permissionLevelText = scanner.nextLine().toLowerCase();
        PermissionLevel permissionLevel = PermissionLevel.valueOf(permissionLevelText.equals("user") ? permissionLevelText.toUpperCase() : "ADMINISTRATOR");

        UserList.getInstance().add(new User(argument.get(0), argument.get(1), permissionLevel));
    }
}
