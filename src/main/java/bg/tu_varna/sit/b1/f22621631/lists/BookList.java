package main.java.bg.tu_varna.sit.b1.f22621631.lists;

import main.java.bg.tu_varna.sit.b1.f22621631.exceptions.lists.BookNotFoundException;
import main.java.bg.tu_varna.sit.b1.f22621631.models.books.Book;

import java.util.ArrayList;
import java.util.List;

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

    public void clear() {
        bookList.clear();
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
        throw new BookNotFoundException("Book not found!");
    }

    public boolean bookExists(String isbn) {
        if (bookList.isEmpty()) {
            return false;
        }

        for (Book currentBook : bookList) {
            if (currentBook.getIsbn().equals(isbn)) {
                return true;
            }
        }
        return false;
    }
}
