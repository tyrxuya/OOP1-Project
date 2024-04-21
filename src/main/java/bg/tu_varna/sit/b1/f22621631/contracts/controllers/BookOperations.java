package main.java.bg.tu_varna.sit.b1.f22621631.contracts.controllers;

import main.java.bg.tu_varna.sit.b1.f22621631.models.books.Book;

public interface BookOperations {
    void all();
    void add();
    void remove();
    void info(String isbn);
    void find(String criteria, String value);
    void sort(String criteria, Boolean ascending);
}
