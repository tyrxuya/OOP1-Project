package main.java.bg.tu_varna.sit.b1.f22621631.commands.factories;

import main.java.bg.tu_varna.sit.b1.f22621631.commands.Command;
import main.java.bg.tu_varna.sit.b1.f22621631.commands.book.*;
import main.java.bg.tu_varna.sit.b1.f22621631.commands.user.*;
import main.java.bg.tu_varna.sit.b1.f22621631.commands.utility.main.*;
import main.java.bg.tu_varna.sit.b1.f22621631.contracts.controllers.RunnableCommand;

import java.util.List;

public class CommandFactory {
    private static CommandFactory instance = null;

    private CommandFactory() {}

    public static CommandFactory getInstance() {
        if (instance == null) {
            instance = new CommandFactory();
        }
        return instance;
    }

    public RunnableCommand getCommand(Command command, List<String> arguments) {
        return switch (command) {
            case OPEN -> new Open(arguments);
            case CLOSE -> new Close();
            case SAVE -> new Save();
            case SAVE_AS -> new SaveAs(arguments);
            case HELP -> new Help();
            case EXIT -> new Exit();

            case BOOKS_ALL, BOOKS_INFO -> new DisplayBook(arguments);
            case BOOKS_ADD -> new AddBook();
            case BOOKS_REMOVE -> new RemoveBook(arguments);
            case BOOKS_FIND -> new FindBook(arguments);
            case BOOKS_SORT -> new SortBook(arguments);

            case LOGIN -> new Login();
            case LOGOUT -> new Logout();
            case USERS_ADD -> new AddUser(arguments);
            case USERS_REMOVE -> new RemoveUser(arguments);
        };
    }
}
