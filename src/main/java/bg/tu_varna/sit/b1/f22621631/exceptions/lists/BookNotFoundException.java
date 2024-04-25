package main.java.bg.tu_varna.sit.b1.f22621631.exceptions.lists;

import main.java.bg.tu_varna.sit.b1.f22621631.exceptions.LibraryException;

public class BookNotFoundException extends LibraryException {
    public BookNotFoundException(String message) {
        super(message);
    }
}
