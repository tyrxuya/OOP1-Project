package main.java.bg.tu_varna.sit.b1.f22621631.commands.book;

import main.java.bg.tu_varna.sit.b1.f22621631.commands.utility.data.AppData;
import main.java.bg.tu_varna.sit.b1.f22621631.contracts.controllers.RunnableCommand;
import main.java.bg.tu_varna.sit.b1.f22621631.exceptions.commands.IllegalArgumentsException;
import main.java.bg.tu_varna.sit.b1.f22621631.exceptions.files.BookFileNotOpenedException;
import main.java.bg.tu_varna.sit.b1.f22621631.exceptions.files.WrongFileOpenedException;
import main.java.bg.tu_varna.sit.b1.f22621631.exceptions.lists.BookNotFoundException;
import main.java.bg.tu_varna.sit.b1.f22621631.exceptions.lists.UserNotFoundException;
import main.java.bg.tu_varna.sit.b1.f22621631.lists.BookList;
import main.java.bg.tu_varna.sit.b1.f22621631.models.books.Book;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

public class FindBook implements RunnableCommand {
    private List<String> arguments;

    public FindBook(List<String> arguments) {
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
            throw new UserNotFoundException("Cannot perform BOOKS_FIND without having been logged in!"); //UserNotFoundException
        }

        Book searchedBook;
        switch (arguments.get(0)) {
            case "title" -> {
                StringBuilder title = new StringBuilder();
                for (String string : arguments) {
                    if (string.equals("title")) {
                        continue;
                    }
                    title.append(string).append(" ");
                }
                title.deleteCharAt(title.length() - 1);
                searchedBook = searchByTitle(title.toString());
            }
            case "author" -> {
                String author = arguments.get(1) + " " + arguments.get(2);
                searchedBook = searchByAuthor(author);
            }
            case "tag" -> {
                searchedBook = searchByTag(arguments.get(1));
            }
            default -> throw new IllegalArgumentsException("Invalid criteria!"); //IllegalArgumentsException
        }

        System.out.println(searchedBook);
    }

    private Book searchByTitle(String value) {
        for (Book book : BookList.getInstance().getBookList()) {
            if (book.getTitle().equalsIgnoreCase(value)) {
                return book;
            }
        }
        throw new BookNotFoundException("Book with such title not found!"); //BookNotFoundException...
    }

    private Book searchByAuthor(String value) {
        for (Book book : BookList.getInstance().getBookList()) {
            if (book.getAuthor().getName().equalsIgnoreCase(value)) {
                return book;
            }
        }
        throw new BookNotFoundException("Book with such author not found!"); //InvalidBookException
    }

    private Book searchByTag(String value) {
        for (Book book : BookList.getInstance().getBookList()) {
            List<String> keyWords = Arrays.stream(book.getKeyWords().split(", ")).toList();
            for (String keyWord : keyWords) {
                if (keyWord.equalsIgnoreCase(value)) {
                    return book;
                }
            }
        }
        throw new BookNotFoundException("Book with such tag not found!"); //InvalidBookException
    }
}
