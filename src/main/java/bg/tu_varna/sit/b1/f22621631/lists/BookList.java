package main.java.bg.tu_varna.sit.b1.f22621631.lists;

import main.java.bg.tu_varna.sit.b1.f22621631.models.books.Book;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BookList {
    private static BookList instance;
    private Set<Book> bookList;

    private BookList() {
        bookList = new HashSet<>();
    }

    public static BookList getInstance() {
        if (instance == null) {
            instance = new BookList();
        }
        return instance;
    }

    public List<Book> getBookList() {
        return new ArrayList<>(bookList);
    }

    public void add(Book book) {
        if (!bookExists(book)) {
            bookList.add(book);
        }
    }

    public boolean bookExists(Book book) {
        for (Book currentBook : bookList) {
            if (currentBook.getIsbn().equals(book.getIsbn())) {
                return true;
            }
        }
        return false;
    }
}
