package main.java.bg.tu_varna.sit.b1.f22621631.commands.user;

import main.java.bg.tu_varna.sit.b1.f22621631.commands.utility.data.AppData;
import main.java.bg.tu_varna.sit.b1.f22621631.contracts.controllers.RunnableCommand;
import main.java.bg.tu_varna.sit.b1.f22621631.exceptions.commands.AlreadyLoggedInException;
import main.java.bg.tu_varna.sit.b1.f22621631.exceptions.files.UserFileNotOpenedException;
import main.java.bg.tu_varna.sit.b1.f22621631.exceptions.files.WrongFileOpenedException;
import main.java.bg.tu_varna.sit.b1.f22621631.exceptions.models.users.InvalidUsernameException;
import main.java.bg.tu_varna.sit.b1.f22621631.exceptions.models.users.WrongCredentialsException;
import main.java.bg.tu_varna.sit.b1.f22621631.lists.UserList;
import main.java.bg.tu_varna.sit.b1.f22621631.users.User;

import java.io.Console;
import java.io.FileNotFoundException;
import java.util.Arrays;

public class Login implements RunnableCommand {
    @Override
    public void execute() {
        if (AppData.getInstance().getOpenedFile() == null) {
            throw new UserFileNotOpenedException("Cannot perform user operations without opening the file!");
        }

        if (AppData.getInstance().getOpenedFile().getName().equals("books.xml")) {
            throw new WrongFileOpenedException("Cannot perform user operations while working on books file!");
        }

        if (AppData.getInstance().getActiveUser() != null) {
            throw new AlreadyLoggedInException("Already logged in!");
        }

        Console console = System.console();

        String username = console.readLine("Enter username: ");

        if (!UserList.getInstance().userExists(username)) {
            throw new InvalidUsernameException("No user with such username found!");
        }

        char[] password = console.readPassword("Enter password: ");
        String passwordText = new String(password);

        User attemptedUser = UserList.getInstance().findUser(username);
        if (!attemptedUser.getPassword().equals(passwordText)) {
            throw new WrongCredentialsException("Wrong credentials!"); //WrongCredentialsException
        }
        AppData.getInstance().setActiveUser(attemptedUser);
        System.out.println("Welcome " + username + "!");
    }
}
