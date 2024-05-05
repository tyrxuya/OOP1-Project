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

        if (arguments.isEmpty()) {
            throw new WrongSyntaxException("Wrong syntax! Expected: books find <title/author/tag> <value>");
        }

        String criteria = arguments.get(0);

        if (!criteria.equalsIgnoreCase("title") && !criteria.equalsIgnoreCase("author") && !criteria.equalsIgnoreCase("tag")) {
            throw new WrongSyntaxException("Wrong syntax! Expected: books find <title/author/tag> <value>");
        }

        if (arguments.size() == 1) {
            throw new WrongSyntaxException("Wrong syntax! Expected: books find <title/author/tag> <value>");
        }

        String searchCriteria = String.join(" ", arguments.subList(1, arguments.size()));

        arguments = new ArrayList<>();

        arguments.add(criteria);
        arguments.add(searchCriteria);

        List<Book> searchedBooks;
        switch (arguments.get(0).toLowerCase()) {
            case "title" -> {
                searchedBooks = searchByTitle(arguments.get(1));
            }
            case "author" -> {
                searchedBooks = searchByAuthor(arguments.get(1));
            }
            case "tag" -> {
                searchedBooks = searchByTag(arguments.get(1));
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
