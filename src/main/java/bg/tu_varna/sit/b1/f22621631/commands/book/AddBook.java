package main.java.bg.tu_varna.sit.b1.f22621631.commands.book;

import main.java.bg.tu_varna.sit.b1.f22621631.commands.utility.data.AppData;
import main.java.bg.tu_varna.sit.b1.f22621631.contracts.controllers.RunnableCommand;
import main.java.bg.tu_varna.sit.b1.f22621631.exceptions.files.BookFileNotOpenedException;
import main.java.bg.tu_varna.sit.b1.f22621631.exceptions.files.WrongFileOpenedException;
import main.java.bg.tu_varna.sit.b1.f22621631.exceptions.lists.UserNotFoundException;
import main.java.bg.tu_varna.sit.b1.f22621631.exceptions.models.authors.InvalidAuthorException;
import main.java.bg.tu_varna.sit.b1.f22621631.exceptions.models.authors.InvalidCountryException;
import main.java.bg.tu_varna.sit.b1.f22621631.exceptions.models.books.InvalidBookIsbnException;
import main.java.bg.tu_varna.sit.b1.f22621631.exceptions.models.books.InvalidBookTitleException;
import main.java.bg.tu_varna.sit.b1.f22621631.exceptions.models.books.InvalidBookYearException;
import main.java.bg.tu_varna.sit.b1.f22621631.exceptions.models.books.InvalidRatingException;
import main.java.bg.tu_varna.sit.b1.f22621631.exceptions.models.users.InvalidPermissionLevelException;
import main.java.bg.tu_varna.sit.b1.f22621631.lists.BookList;
import main.java.bg.tu_varna.sit.b1.f22621631.models.authors.Author;
import main.java.bg.tu_varna.sit.b1.f22621631.models.books.Book;
import main.java.bg.tu_varna.sit.b1.f22621631.models.books.enums.Genre;
import main.java.bg.tu_varna.sit.b1.f22621631.models.books.enums.Rating;

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
        System.out.print("Enter author name: ");
        String authorName = scanner.nextLine();

        if (Objects.isNull(authorName)) {
            throw new InvalidAuthorException("Invalid author name!");
        }

        System.out.print("Enter author country: ");
        String authorCountry = scanner.nextLine();

        if (Objects.isNull(authorCountry)) {
            throw new InvalidCountryException("Invalid country!");
        }

        System.out.print("Enter book title: ");
        String bookTitle = scanner.nextLine();

        if (Objects.isNull(bookTitle)) {
            throw new InvalidBookTitleException("Invalid book title!");
        }

        System.out.print("Enter book publishing year: ");
        Integer bookYear = scanner.nextInt();
        scanner.nextLine();

        if (bookYear <= 0) {
            throw new InvalidBookYearException("Year cannot be negative!");
        }
        System.out.print("Enter book ISBN: ");
        String bookIsbn = scanner.nextLine();

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

        System.out.print("Enter genre (to skip press \"s\"): ");
        String tempGenre = scanner.nextLine();
        Genre bookGenre = (tempGenre.equals("s") ? null : Genre.valueOf(tempGenre.toUpperCase().replace(" ", "_")));

        System.out.print("Enter description (to skip press \"s\"): ");
        String tempDescription = scanner.nextLine();
        String bookDescription = (tempDescription.equals("s") ? null : tempDescription);

        System.out.print("Enter key words (to skip press \"s\"): ");
        String tempKeyWords = scanner.nextLine();
        String bookKeyWords = (tempKeyWords.equals("s") ? null : tempKeyWords);

        System.out.print("Enter rating (to skip press \"s\"): ");
        String tempRating = scanner.nextLine();
        Rating bookRating = (tempRating.equals("s") ? null : switch (tempRating) {
            case "0.0" -> Rating.NO_RATING;
            case "1.0" -> Rating.ONE;
            case "1.5" -> Rating.ONE_POINT_FIVE;
            case "2.0" -> Rating.TWO;
            case "2.5" -> Rating.TWO_POINT_FIVE;
            case "3.0" -> Rating.THREE;
            case "3.5" -> Rating.THREE_POINT_FIVE;
            case "4.0" -> Rating.FOUR;
            case "4.5" -> Rating.FOUR_POINT_FIVE;
            case "5.0" -> Rating.FIVE;
            default -> throw new InvalidRatingException("Unexpected value: " + tempRating); //InvalidRatingException
        });

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
}
