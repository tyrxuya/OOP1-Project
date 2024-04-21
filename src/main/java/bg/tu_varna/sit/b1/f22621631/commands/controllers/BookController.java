package main.java.bg.tu_varna.sit.b1.f22621631.commands.controllers;

import main.java.bg.tu_varna.sit.b1.f22621631.contracts.controllers.BookOperations;
import main.java.bg.tu_varna.sit.b1.f22621631.lists.BookList;
import main.java.bg.tu_varna.sit.b1.f22621631.models.authors.Author;
import main.java.bg.tu_varna.sit.b1.f22621631.models.books.Book;
import main.java.bg.tu_varna.sit.b1.f22621631.models.books.enums.Genre;
import main.java.bg.tu_varna.sit.b1.f22621631.models.books.enums.Rating;

import java.util.*;

public class BookController implements BookOperations {
    private final BookList books = BookList.getInstance();

    @Override
    public void all() {
        System.out.println("Available books in library:");
        Integer index = 1;
        for (Book book : books.getBookList()) {
            System.out.println(index++);
            System.out.println(book);
        }
    }

    @Override
    public void add() {
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

        books.add(newBook);
    }

    @Override
    public void remove() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter book ISBN: ");
        String isbn = scanner.nextLine();

        Book bookToRemove = books.findBook(isbn);

        if (Objects.nonNull(bookToRemove)) {
            books.remove(bookToRemove);
        }
        else {
            throw new IllegalArgumentException("Book not found!");
        }
    }

    @Override
    public void info(String isbn) {
        Book searchedBook = books.findBook(isbn);
        System.out.println(Objects.nonNull(searchedBook) ? searchedBook : "Book not found!");
    }

    @Override
    public void find(String criteria, String value) {
        criteria = criteria.toLowerCase();
        Book searchedBook = null;
        switch (criteria) {
            case "title" -> {
                searchedBook = searchByTitle(value);
            }
            case "author" -> {
                searchedBook = searchByAuthor(value);
            }
            case "tag" -> {
                searchedBook = searchByTag(value);
            }
        }
        if (Objects.nonNull(searchedBook)) {
            System.out.println("Book found!");
            System.out.println(searchedBook);
        }
        else {
            System.out.println("Book not found!");
        }
    }

    private Book searchByTitle(String value) {
        for (Book book : books.getBookList()) {
            if (book.getTitle().equalsIgnoreCase(value)) {
                return book;
            }
        }
        return null;
    }

    private Book searchByAuthor(String value) {
        for (Book book : books.getBookList()) {
            if (book.getAuthor().getName().equalsIgnoreCase(value)) {
                return book;
            }
        }
        return null;
    }

    private Book searchByTag(String value) {
        for (Book book : books.getBookList()) {
            if (book.getKeyWords().contains(value)) {
                return book;
            }
        }
        return null;
    }

    @Override
    public void sort(String criteria, Boolean ascending) {
        List<Book> tempBookList = books.getBookList();
        Comparator<Book> bookComparator = null;
        switch (criteria) {
            case "title" -> {
                bookComparator = new Comparator<Book>() {
                    @Override
                    public int compare(Book o1, Book o2) {
                        return o1.getTitle().compareTo(o2.getTitle());
                    }
                };
            }
            case "author" -> {
                bookComparator = new Comparator<Book>() {
                    @Override
                    public int compare(Book o1, Book o2) {
                        return o1.getAuthor().getName().compareTo(o2.getAuthor().getName());
                    }
                };
            }
            case "year" -> {
                bookComparator = new Comparator<Book>() {
                    @Override
                    public int compare(Book o1, Book o2) {
                        return Integer.compare(o1.getPublishingYear(), o2.getPublishingYear());
                    }
                };
            }
            case "rating" -> {
                bookComparator = new Comparator<Book>() {
                    @Override
                    public int compare(Book o1, Book o2) {
                        return o1.getRating().compareTo(o2.getRating());
                    }
                };
            }
        }
        tempBookList.sort(ascending ? bookComparator : Collections.reverseOrder(bookComparator));
        books.setBookList(tempBookList);
    }
}
