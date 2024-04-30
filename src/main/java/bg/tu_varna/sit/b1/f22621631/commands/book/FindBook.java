package main.java.bg.tu_varna.sit.b1.f22621631.commands.book;

import main.java.bg.tu_varna.sit.b1.f22621631.commands.utility.data.AppData;
import main.java.bg.tu_varna.sit.b1.f22621631.contracts.controllers.RunnableCommand;
import main.java.bg.tu_varna.sit.b1.f22621631.exceptions.commands.IllegalArgumentsException;
import main.java.bg.tu_varna.sit.b1.f22621631.exceptions.commands.WrongSyntaxException;
import main.java.bg.tu_varna.sit.b1.f22621631.exceptions.files.BookFileNotOpenedException;
import main.java.bg.tu_varna.sit.b1.f22621631.exceptions.files.WrongFileOpenedException;
import main.java.bg.tu_varna.sit.b1.f22621631.exceptions.lists.BookNotFoundException;
import main.java.bg.tu_varna.sit.b1.f22621631.exceptions.lists.UserNotFoundException;
import main.java.bg.tu_varna.sit.b1.f22621631.lists.BookList;
import main.java.bg.tu_varna.sit.b1.f22621631.models.books.Book;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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

        if (arguments.isEmpty() || arguments.size() == 1) {
            throw new WrongSyntaxException("Wrong syntax! Expected: books find <title/author/tag> <value>");
        }

        List<Book> searchedBooks = new ArrayList<>();
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
                searchedBooks = searchByTitle(title.toString());
            }
            case "author" -> {
                String author = arguments.get(1) + " " + arguments.get(2);
                searchedBooks = searchByAuthor(author);
            }
            case "tag" -> {
                StringBuilder tags = new StringBuilder();
                for (int i = 1; i < arguments.size(); i++) {
                    tags.append(arguments.get(i)).append(" ");
                }
                tags.deleteCharAt(tags.lastIndexOf(" "));
                searchedBooks = searchByTag(tags.toString());
            }
            default -> throw new IllegalArgumentsException("Invalid criteria!"); //IllegalArgumentsException
        }

        System.out.println("Books found:");
        int index = 1;
        for (Book book : searchedBooks) {
            System.out.println(index++ + ".");
            System.out.println(book);
            System.out.println();
        }
    }

    private List<Book> searchByTitle(String value) {
        List<Book> temp = new ArrayList<>();
        for (Book book : BookList.getInstance().getBookList()) {
            if (book.getTitle().equalsIgnoreCase(value)) {
                temp.add(book);
            }
        }
        if (temp.isEmpty()) {
            throw new BookNotFoundException("Book with such title not found!"); //BookNotFoundException...
        }
        return temp;
    }

    private List<Book> searchByAuthor(String value) {
        List<Book> temp = new ArrayList<>();
        for (Book book : BookList.getInstance().getBookList()) {
            if (book.getAuthor().getName().equalsIgnoreCase(value)) {
                temp.add(book);
            }
        }
        if (temp.isEmpty()) {
            throw new BookNotFoundException("Book with such author not found!"); //InvalidBookException
        }
        return temp;
    }

    private List<Book> searchByTag(String value) {
        List<Book> temp = new ArrayList<>();
        for (Book book : BookList.getInstance().getBookList()) {
            if (Objects.isNull(book.getKeyWords())) {
                continue;
            }
            List<String> keyWords = Arrays.stream(book.getKeyWords().split(", ")).toList();
            for (String keyWord : keyWords) {
                if (keyWord.equalsIgnoreCase(value)) {
                    temp.add(book);
                }
            }
        }
        if (temp.isEmpty()) {
            throw new BookNotFoundException("Book with such tag not found!"); //InvalidBookException
        }
        return temp;
    }
}
