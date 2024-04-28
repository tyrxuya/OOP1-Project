package main.java.bg.tu_varna.sit.b1.f22621631.commands;

import main.java.bg.tu_varna.sit.b1.f22621631.users.PermissionLevel;

public enum Command {
    OPEN ("open", "Opens a file and reads its contents.", PermissionLevel.NONE),
    CLOSE ("close", "Closes the current file.", PermissionLevel.NONE),
    SAVE ("save", "Saves the contents in the current file.", PermissionLevel.NONE),
    SAVE_AS ("save as", "Saves the contents in a directory, chosen by the user.", PermissionLevel.NONE),
    HELP ("help", "Shows all possible commands.", PermissionLevel.NONE),
    EXIT ("exit", "Exits the application.", PermissionLevel.NONE),
    LOGIN ("login", "Login to the application after being prompted with username and password.", PermissionLevel.NONE),
    LOGOUT ("logout", "Logout of the current user.", PermissionLevel.USER),
    BOOKS_ALL ("books all", "Prints all the available books.", PermissionLevel.USER),
    BOOKS_VIEW ("books view", "Prints all the available books.", PermissionLevel.USER),
    BOOKS_ADD ("books add", "Adds a book to the library.", PermissionLevel.ADMINISTRATOR),
    BOOKS_REMOVE ("books remove", "Removes a book from the library by a given <ISBN_Value>.", PermissionLevel.ADMINISTRATOR),
    BOOKS_INFO ("books info", "Prints all the information on a book by a given <ISBN_Value>.", PermissionLevel.USER),
    BOOKS_FIND ("books find", "Searches for a book by <title/author/tag>.", PermissionLevel.USER),
    BOOKS_SORT ("books sort", "Sorts the books by <title/author/year/rating> in <ASC(default)/DESC> order.", PermissionLevel.USER),
    USERS_ADD ("users add", "Adds a user with <username> and <password> to the file.", PermissionLevel.ADMINISTRATOR),
    USERS_REMOVE ("users remove", "Removes a user with <username> from a file.", PermissionLevel.ADMINISTRATOR);
    private final String command;
    private final String description;
    private final PermissionLevel permissionLevel;

    Command(String command, String description, PermissionLevel permissionLevel) {
        this.command = command;
        this.description = description;
        this.permissionLevel = permissionLevel;
    }

    public String getCommand() {
        return command;
    }

    public String getDescription() {
        return description;
    }

    public PermissionLevel getPermissionLevel() {
        return permissionLevel;
    }
}
