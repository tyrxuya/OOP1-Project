package main.java.bg.tu_varna.sit.b1.f22621631.exceptions.models.authors;

import main.java.bg.tu_varna.sit.b1.f22621631.exceptions.LibraryException;

public class InvalidAuthorException extends LibraryException {
    public InvalidAuthorException(String message) {
        super(message);
    }
}
