package main.java.bg.tu_varna.sit.b1.f22621631.commands.book;

import main.java.bg.tu_varna.sit.b1.f22621631.commands.utility.data.AppData;
import main.java.bg.tu_varna.sit.b1.f22621631.contracts.controllers.RunnableCommand;
import main.java.bg.tu_varna.sit.b1.f22621631.exceptions.files.BookFileNotOpenedException;
import main.java.bg.tu_varna.sit.b1.f22621631.exceptions.files.WrongFileOpenedException;
import main.java.bg.tu_varna.sit.b1.f22621631.exceptions.lists.UserNotFoundException;
import main.java.bg.tu_varna.sit.b1.f22621631.lists.BookList;
import main.java.bg.tu_varna.sit.b1.f22621631.models.books.Book;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileNotFoundException;
import java.util.List;

public class DisplayBook implements RunnableCommand {
    private List<String> arguments;

    public DisplayBook(List<String> arguments) {
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
            throw new UserNotFoundException("Cannot perform this operation without having been logged in!"); //UserNotFoundException
        }

        printAllBooks();
    }

    private void printAllBooks() {
        System.out.println("Available books in library:");
        int index = 1;
        for (Book book : BookList.getInstance().getBookList()) {
            System.out.println(index++ + ".");
            System.out.println(book);
            System.out.println();
        }
    }
}
