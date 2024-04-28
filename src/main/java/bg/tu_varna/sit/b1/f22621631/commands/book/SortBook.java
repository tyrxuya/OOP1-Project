package main.java.bg.tu_varna.sit.b1.f22621631.commands.book;

import main.java.bg.tu_varna.sit.b1.f22621631.commands.utility.data.AppData;
import main.java.bg.tu_varna.sit.b1.f22621631.contracts.controllers.RunnableCommand;
import main.java.bg.tu_varna.sit.b1.f22621631.exceptions.commands.WrongSyntaxException;
import main.java.bg.tu_varna.sit.b1.f22621631.exceptions.files.BookFileNotOpenedException;
import main.java.bg.tu_varna.sit.b1.f22621631.exceptions.files.WrongFileOpenedException;
import main.java.bg.tu_varna.sit.b1.f22621631.exceptions.lists.UserNotFoundException;
import main.java.bg.tu_varna.sit.b1.f22621631.lists.BookList;
import main.java.bg.tu_varna.sit.b1.f22621631.models.books.Book;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortBook implements RunnableCommand {
    private List<String> arguments;

    public SortBook(List<String> arguments) {
        this.arguments = arguments;
    }

    @Override
    public void execute() {
        if (AppData.getInstance().getOpenedFile() == null) {
            throw new BookFileNotOpenedException("Cannot perform book operations without opening the file!");
        }

        if (AppData.getInstance().getOpenedFile().getName().equals("users.xml")) {
            throw new WrongFileOpenedException("Cannot perform book operations while working on users file!");
        }

        if (AppData.getInstance().getActiveUser() == null) {
            throw new UserNotFoundException("Cannot perform BOOKS_SORT without having been logged in!");
        }

        if (arguments.isEmpty()) {
            throw new WrongSyntaxException("Wrong syntax! Expected: books sort <title/author/year/rating> [<asc/desc>]");
        }

        boolean ascending = arguments.size() != 2 || (arguments.get(1).equalsIgnoreCase("asc"));
        List<Book> tempBookList = BookList.getInstance().getBookList();
        Comparator<Book> bookComparator = getBookComparator();
        tempBookList.sort(ascending ? bookComparator : Collections.reverseOrder(bookComparator));
        BookList.getInstance().setBookList(tempBookList);
        System.out.println("Sort successful!");
    }

    private Comparator<Book> getBookComparator() {
        Comparator<Book> bookComparator = null;
        switch (arguments.get(0)) {
            case "title" -> {
                bookComparator = new Comparator<Book>() {
                    @Override
                    public int compare(Book o1, Book o2) {
                        return o1.getTitle().compareTo(o2.getTitle());
                    }
                };
            }
            case "author" -> {
                bookComparator = new Comparator<Book>() {
                    @Override
                    public int compare(Book o1, Book o2) {
                        return o1.getAuthor().getName().compareTo(o2.getAuthor().getName());
                    }
                };
            }
            case "year" -> {
                bookComparator = new Comparator<Book>() {
                    @Override
                    public int compare(Book o1, Book o2) {
                        return Integer.compare(o1.getPublishingYear(), o2.getPublishingYear());
                    }
                };
            }
            case "rating" -> {
                bookComparator = new Comparator<Book>() {
                    @Override
                    public int compare(Book o1, Book o2) {
                        return o1.getRating().compareTo(o2.getRating());
                    }
                };
            }
            default -> throw new WrongSyntaxException("Wrong syntax: books sort <TITLE/AUTHOR/YEAR/RATING> [<ASC/DESC>]");
        }
        return bookComparator;
    }
}
