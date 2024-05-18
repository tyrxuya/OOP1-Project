package main.java.bg.tu_varna.sit.b1.f22621631.commands.user;

import main.java.bg.tu_varna.sit.b1.f22621631.commands.utility.data.AppData;
import main.java.bg.tu_varna.sit.b1.f22621631.contracts.controllers.RunnableCommand;
import main.java.bg.tu_varna.sit.b1.f22621631.exceptions.commands.IllegalArgumentsException;
import main.java.bg.tu_varna.sit.b1.f22621631.exceptions.commands.WrongSyntaxException;
import main.java.bg.tu_varna.sit.b1.f22621631.exceptions.files.UserFileNotOpenedException;
import main.java.bg.tu_varna.sit.b1.f22621631.exceptions.files.WrongFileOpenedException;
import main.java.bg.tu_varna.sit.b1.f22621631.exceptions.models.users.InvalidPermissionLevelException;
import main.java.bg.tu_varna.sit.b1.f22621631.exceptions.models.users.InvalidUserException;
import main.java.bg.tu_varna.sit.b1.f22621631.lists.UserList;
import main.java.bg.tu_varna.sit.b1.f22621631.users.PermissionLevel;
import main.java.bg.tu_varna.sit.b1.f22621631.users.User;

import java.util.List;
import java.util.Scanner;

public class AddUser implements RunnableCommand {
    private final List<String> arguments;

    public AddUser(List<String> arguments) {
        this.arguments = arguments;
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

        if (arguments.size() != 2) {
            throw new WrongSyntaxException("Wrong syntax! Expected: users add <userName> <password>");
        }

        if (UserList.getInstance().userExists(arguments.get(0))) {
            throw new InvalidUserException("User with such username already exists!"); //LoginException
        }

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter permission level (admin or user): ");
        String permissionLevelText = scanner.nextLine().toLowerCase();
        if (!permissionLevelText.equalsIgnoreCase("admin") && !permissionLevelText.equalsIgnoreCase("user")) {
            throw new IllegalArgumentsException("Illegal arguments: expected ADMIN or USER");
        }
        PermissionLevel permissionLevel = PermissionLevel.valueOf(permissionLevelText.equalsIgnoreCase("user") ? permissionLevelText.toUpperCase() : "ADMINISTRATOR");

        UserList.getInstance().add(new User(arguments.get(0), arguments.get(1), permissionLevel));

        System.out.println("User added successfully!");
    }
}
