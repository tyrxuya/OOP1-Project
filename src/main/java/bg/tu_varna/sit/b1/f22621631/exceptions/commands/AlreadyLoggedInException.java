package main.java.bg.tu_varna.sit.b1.f22621631.exceptions.commands;

import main.java.bg.tu_varna.sit.b1.f22621631.exceptions.LibraryException;

public class AlreadyLoggedInException extends LibraryException {
    public AlreadyLoggedInException(String message) {
        super(message);
    }
}
