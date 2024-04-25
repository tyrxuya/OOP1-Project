package main.java.bg.tu_varna.sit.b1.f22621631.exceptions.models.books;

import main.java.bg.tu_varna.sit.b1.f22621631.exceptions.LibraryException;

public class InvalidBookYearException extends LibraryException {
    public InvalidBookYearException(String message) {
        super(message);
    }
}
