package main.java.bg.tu_varna.sit.b1.f22621631.commands.book;

import main.java.bg.tu_varna.sit.b1.f22621631.commands.utility.data.AppData;
import main.java.bg.tu_varna.sit.b1.f22621631.contracts.controllers.RunnableCommand;
import main.java.bg.tu_varna.sit.b1.f22621631.lists.BookList;
import main.java.bg.tu_varna.sit.b1.f22621631.models.authors.Author;
import main.java.bg.tu_varna.sit.b1.f22621631.models.books.Book;
import main.java.bg.tu_varna.sit.b1.f22621631.models.books.enums.Genre;
import main.java.bg.tu_varna.sit.b1.f22621631.models.books.enums.Rating;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

public class AddBook implements RunnableCommand {
    @Override
    public void execute() throws Exception {
        if (AppData.getInstance().getOpenedFile() == null) {
            throw new FileNotFoundException("Cannot perform book operations without opening the file!");
        }

        if (AppData.getInstance().getOpenedFile().getName().equals("users.xml")) {
            throw new Exception("Cannot perform book operations while working on users file!");
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter author name: ");
        String authorName = scanner.nextLine();
        System.out.print("Enter author country: ");
        String authorCountry = scanner.nextLine();

        System.out.print("Enter book title: ");
        String bookTitle = scanner.nextLine();
        System.out.print("Enter book publishing year: ");
        Integer bookYear = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter book ISBN: ");
        String bookIsbn = scanner.nextLine();

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

        System.out.println("Enter key words (to skip press \"s\"): ");
        String tempKeyWords = scanner.nextLine();
        String bookKeyWords = (tempKeyWords.equals("s") ? null : tempKeyWords);

        System.out.println("Enter rating (to skip press \"s\"): ");
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
            default -> throw new IllegalStateException("Unexpected value: " + tempRating);
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
