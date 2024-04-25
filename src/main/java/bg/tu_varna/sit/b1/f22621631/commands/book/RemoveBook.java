package main.java.bg.tu_varna.sit.b1.f22621631.commands.book;

import main.java.bg.tu_varna.sit.b1.f22621631.commands.utility.data.AppData;
import main.java.bg.tu_varna.sit.b1.f22621631.contracts.controllers.RunnableCommand;
import main.java.bg.tu_varna.sit.b1.f22621631.lists.BookList;

import java.io.FileNotFoundException;
import java.util.List;

public class RemoveBook implements RunnableCommand {
    private List<String> arguments;

    public RemoveBook(List<String> arguments) {
        this.arguments = arguments;
    }

    @Override
    public void execute() throws Exception {
        if (AppData.getInstance().getOpenedFile() == null) {
            throw new FileNotFoundException("Cannot perform book operations without opening the file!"); //BookFileNotOpenedException
        }

        if (AppData.getInstance().getOpenedFile().getName().equals("users.xml")) {
            throw new Exception("Cannot perform book operations while working on users file!"); //WrongFileOpenedException
        }

        if (AppData.getInstance().getActiveUser() == null) {
            throw new Exception("Cannot remove book without being logged in!"); //UserNotFoundException
        }

        if (AppData.getInstance().getActiveUser().getPermissionLevel().getText().equals("User")) {
            throw new Exception("Access denied, ADMINISTRATOR permission required!"); //PermissionLevelException
        }

        BookList books = BookList.getInstance();
        books.remove(books.findBook(arguments.get(0)));
    }
}
