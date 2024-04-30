package main.java.bg.tu_varna.sit.b1.f22621631.commands.book;

import main.java.bg.tu_varna.sit.b1.f22621631.commands.utility.data.AppData;
import main.java.bg.tu_varna.sit.b1.f22621631.contracts.controllers.RunnableCommand;
import main.java.bg.tu_varna.sit.b1.f22621631.exceptions.commands.WrongSyntaxException;
import main.java.bg.tu_varna.sit.b1.f22621631.exceptions.files.BookFileNotOpenedException;
import main.java.bg.tu_varna.sit.b1.f22621631.exceptions.files.WrongFileOpenedException;
import main.java.bg.tu_varna.sit.b1.f22621631.exceptions.lists.UserNotFoundException;
import main.java.bg.tu_varna.sit.b1.f22621631.exceptions.models.users.InvalidPermissionLevelException;
import main.java.bg.tu_varna.sit.b1.f22621631.lists.BookList;

import java.util.List;

public class RemoveBook implements RunnableCommand {
    private List<String> arguments;

    public RemoveBook(List<String> arguments) {
        this.arguments = arguments;
    }

    @Override
    public void execute() {
        if (AppData.getInstance().getOpenedFile() == null) {
            throw new BookFileNotOpenedException("Cannot perform book operations without opening the file!"); //BookFileNotOpenedException
        }

        if (AppData.getInstance().getOpenedFile().getName().equals("users.xml")) {
            throw new WrongFileOpenedException("Cannot perform book operations while working on users file!"); //WrongFileOpenedException
        }

        if (AppData.getInstance().getActiveUser() == null) {
            throw new UserNotFoundException("Cannot remove book without being logged in!"); //UserNotFoundException
        }

        if (AppData.getInstance().getActiveUser().getPermissionLevel().getText().equals("User")) {
            throw new InvalidPermissionLevelException("Access denied, ADMINISTRATOR permission required!"); //InvalidPermissionLevelException
        }

        if (arguments.size() != 1) {
            throw new WrongSyntaxException("Wrong syntax! Expected: books remove <isbn>");
        }

        BookList books = BookList.getInstance();
        books.remove(books.findBook(arguments.get(0)));
        System.out.println("Book successfully removed!");
    }
}
