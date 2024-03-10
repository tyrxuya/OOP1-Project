package main.java.bg.tu_varna.sit.b1.f22621631.commands;

public enum Command {
    OPEN ("open", "Opens a file and reads its contents."),
    CLOSE ("close", "Closes the current file."),
    SAVE ("save", "Saves the contents in the current file."),
    SAVE_AS ("save as", "Saves the contents in a directory, chosen by the user."),
    HELP ("help", "Shows all possible commands."),
    EXIT ("exit", "Exits the application."),
    LOGIN ("login", "Login to the application after being prompted with username and password."),
    LOGOUT ("logout", "Logout of the current user."),
    BOOKS_ALL ("books all", "Prints all the available books."),
    BOOKS_INFO ("books info", "Prints all the information on a book by a given <ISBN_Value>."),
    BOOKS_FIND ("books find", "Searches for a book by <title/author/tag>."),
    BOOKS_SORT ("books sort", "Sorts the books by <title/author/year/rating> in <ASC(default)/DESC> order."),
    USERS_ADD ("users add", "Adds a user with <username> and <password> to the file."),
    USERS_REMOVE ("users remove", "Removes a user with <username> from a file.");

    private final String command;
    private final String description;

    Command(String command, String description) {
        this.command = command;
        this.description = description;
    }

    public String getCommand() {
        return command;
    }

    public String getDescription() {
        return description;
    }
}
