package main.java.bg.tu_varna.sit.b1.f22621631.lists;

import main.java.bg.tu_varna.sit.b1.f22621631.models.books.Book;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BookList {
    private static BookList instance;
    private List<Book> bookList;

    private BookList() {
        bookList = new ArrayList<>();
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

    public void setBookList(List<Book> bookList) {
        this.bookList = new ArrayList<>(bookList);
    }

    public void add(Book book) {
        if (!bookExists(book.getIsbn())) {
            bookList.add(book);
        }
    }

    public void remove(Book book) {
        if (bookExists(book.getIsbn())) {
            bookList.remove(book);
        }
    }

    public Book findBook(String isbn) {
        for (Book book : bookList) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        return null;
    }

    public boolean bookExists(String isbn) {
        for (Book currentBook : bookList) {
            if (currentBook.getIsbn().equals(isbn)) {
                return true;
            }
        }
        return false;
    }
}
