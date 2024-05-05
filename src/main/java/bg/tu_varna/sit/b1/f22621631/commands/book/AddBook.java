package main.java.bg.tu_varna.sit.b1.f22621631.commands.book;

import main.java.bg.tu_varna.sit.b1.f22621631.commands.utility.data.AppData;
import main.java.bg.tu_varna.sit.b1.f22621631.contracts.controllers.RunnableCommand;
import main.java.bg.tu_varna.sit.b1.f22621631.exceptions.files.BookFileNotOpenedException;
import main.java.bg.tu_varna.sit.b1.f22621631.exceptions.files.WrongFileOpenedException;
import main.java.bg.tu_varna.sit.b1.f22621631.exceptions.lists.UserNotFoundException;
import main.java.bg.tu_varna.sit.b1.f22621631.exceptions.models.authors.InvalidAuthorException;
import main.java.bg.tu_varna.sit.b1.f22621631.exceptions.models.authors.InvalidCountryException;
import main.java.bg.tu_varna.sit.b1.f22621631.exceptions.models.books.*;
import main.java.bg.tu_varna.sit.b1.f22621631.exceptions.models.users.InvalidPermissionLevelException;
import main.java.bg.tu_varna.sit.b1.f22621631.lists.BookList;
import main.java.bg.tu_varna.sit.b1.f22621631.models.authors.Author;
import main.java.bg.tu_varna.sit.b1.f22621631.models.books.Book;
import main.java.bg.tu_varna.sit.b1.f22621631.models.books.enums.Genre;
import main.java.bg.tu_varna.sit.b1.f22621631.models.books.enums.Rating;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class AddBook implements RunnableCommand {
    @Override
    public void execute() {
        if (AppData.getInstance().getOpenedFile() == null) {
            throw new BookFileNotOpenedException("Cannot perform book operations without opening the file!"); //BookFileNotOpenedException
        }

        if (AppData.getInstance().getOpenedFile().getName().equals("users.xml")) {
            throw new WrongFileOpenedException("Cannot perform book operations while working on users file!"); //WrongFileOpenedException
        }

        if (AppData.getInstance().getActiveUser() == null) {
            throw new UserNotFoundException("Cannot add book without being logged in!"); //UserNotFoundException
        }

        if (AppData.getInstance().getActiveUser().getPermissionLevel().getText().equals("User")) {
            throw new InvalidPermissionLevelException("Access denied, ADMINISTRATOR permission required!"); //PermissionLevelException
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter author name (first and last name): ");
        String authorName = scanner.nextLine();

        if (Objects.isNull(authorName) || authorName.split(" ").length != 2) {
            throw new InvalidAuthorException("Invalid author name!");
        }

        System.out.print("Enter author country: ");
        String authorCountry = scanner.nextLine();

        if (Objects.isNull(authorCountry) || authorCountry.isEmpty() || authorCountry.isBlank()) {
            throw new InvalidCountryException("Invalid country!");
        }

        System.out.print("Enter book title: ");
        String bookTitle = scanner.nextLine();

        if (Objects.isNull(bookTitle) || bookTitle.isEmpty() || bookTitle.isBlank()) {
            throw new InvalidBookTitleException("Invalid book title!");
        }

        System.out.print("Enter book publishing year: ");
        int bookYear;

        try {
            bookYear = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid year!");
            return;
        }

        if (bookYear <= 0) {
            throw new InvalidBookYearException("Year cannot be negative!");
        }

        //scanner.nextLine();

        System.out.print("Enter book ISBN: ");
        String bookIsbn = scanner.nextLine();

        if (Objects.isNull(bookIsbn) || bookIsbn.isEmpty() || bookIsbn.isBlank()) {
            throw new InvalidBookIsbnException("Invalid ISBN!");
        }

        if (BookList.getInstance().bookExists(bookIsbn)) {
            throw new InvalidBookIsbnException("Book with such ISBN already exists!"); //InvalidBookIsbnException
        }

        Book.Builder tempBook = new Book.Builder(
                new Author(
                        authorName.split(" ")[0],
                        authorName.split(" ")[1],
                        authorCountry
                ),
                bookTitle,
                bookYear,
                bookIsbn
        );

        System.out.println("Enter genre (to skip press \"s\"): ");
        System.out.println("Available genres:");
        printGenres();
        String tempGenre = scanner.nextLine();

        if (!Genre.isValidGenre(tempGenre)) {
            throw new InvalidGenreException("Invalid genre!");
        }

        Genre bookGenre = (tempGenre.equalsIgnoreCase("s") ? null : Genre.valueOf(tempGenre.toUpperCase().replace(" ", "_")));

        System.out.print("Enter description (to skip press \"s\"): ");
        String tempDescription = scanner.nextLine();
        String bookDescription = (tempDescription.equalsIgnoreCase("s") ? null : tempDescription);

        System.out.println("Enter key words (to skip or stop entering press \"s\"):");
        List<String> keyWords = new ArrayList<>();
        String tempKeyWords;
        while (!(tempKeyWords = scanner.nextLine()).equalsIgnoreCase("s")) {
            if (tempKeyWords.isEmpty()) {
                continue;
            }
            keyWords.add(tempKeyWords);
        }
        String bookKeyWords = (keyWords.isEmpty()) ? null : String.join(", ", keyWords);

        System.out.print("Enter rating (to skip press \"s\"): ");
        String tempRating = scanner.nextLine();
        Rating bookRating = (tempRating.equalsIgnoreCase("s") ? null : Rating.getRating(tempRating));

        if (Objects.nonNull(bookGenre)) {
            tempBook.genre(bookGenre);
        }

        if (Objects.nonNull(bookDescription)) {
            tempBook.description(bookDescription);
        }

        if (Objects.nonNull(bookKeyWords)) {
            tempBook.keyWords(bookKeyWords);
        }

        if (Objects.nonNull(bookRating)) {
            tempBook.rating(bookRating);
        }

        Book newBook = tempBook.build();

        BookList.getInstance().add(newBook);

        System.out.println("Book added successfully!");
    }

    private void printGenres() {
        for (Genre genre : Genre.values()) {
            System.out.println(genre.getText());
        }
    }
}
