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
            throw new FileNotFoundException("Cannot perform book operations without opening the file!");
        }

        if (AppData.getInstance().getOpenedFile().getName().equals("users.xml")) {
            throw new Exception("Cannot perform book operations while working on users file!");
        }

        BookList books = BookList.getInstance();
        books.remove(books.findBook(arguments.get(0)));
    }
}
