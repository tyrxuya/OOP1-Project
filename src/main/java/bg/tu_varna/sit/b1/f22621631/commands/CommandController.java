package main.java.bg.tu_varna.sit.b1.f22621631.commands;

import main.java.bg.tu_varna.sit.b1.f22621631.commands.read.ReadBooks;
import main.java.bg.tu_varna.sit.b1.f22621631.commands.write.WriteBooks;
import main.java.bg.tu_varna.sit.b1.f22621631.commands.write.WriteUsers;
import main.java.bg.tu_varna.sit.b1.f22621631.lists.BookList;
import main.java.bg.tu_varna.sit.b1.f22621631.lists.UserList;
import main.java.bg.tu_varna.sit.b1.f22621631.models.authors.Author;
import main.java.bg.tu_varna.sit.b1.f22621631.models.books.Book;
import main.java.bg.tu_varna.sit.b1.f22621631.models.books.enums.Genre;
import main.java.bg.tu_varna.sit.b1.f22621631.models.books.enums.Rating;
import main.java.bg.tu_varna.sit.b1.f22621631.users.User;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;

public class CommandController {
    private static File openedFile = null;

    public static void run(String command) {
        if (command.isBlank()) {
            return;
        }
        String[] commandArgs = command.split(" ");
        String firstArg = commandArgs[0].toLowerCase();
        switch (firstArg) {
            case "books": case "users":
                firstArg += "_" + commandArgs[1].toLowerCase();
                break;
            case "save":
                if (commandArgs.length == 1) {
                    break;
                }
                if (commandArgs[1].equals("as")) {
                    firstArg += "_" + commandArgs[1].toLowerCase();
                }
                break;
        }

        //Here we will call the functionalities later when they are developed. For now, it's used for printing purposes
        try {
            switch (Command.valueOf(firstArg.toUpperCase())) {
                case OPEN -> {
                    String fileName = commandArgs[1];
                    if (!(fileName.equals("books.xml") || fileName.equals("users.xml"))) {
                        throw new FileNotFoundException("The file doesn't exist!");
                    }
                    openedFile = new File(fileName);
                    System.out.println("File " + fileName + " opened successfully!");
                }
                case CLOSE -> {
                    openedFile = null;
                }
                case SAVE -> {
                    if (openedFile == null) {
                        throw new FileNotFoundException("The file doesn't exist!");
                    }
                    switch (openedFile.getName()) {
                        case "books.xml" -> {
                            WriteBooks booksWriter = new WriteBooks();
                            booksWriter.writeFile(BookList.getInstance().getBookList());
                        }
                        case "users.xml" -> {
                            WriteUsers usersWriter = new WriteUsers();
                            usersWriter.writeFile(UserList.getInstance().getUserList());
                        }
                        default -> {
                            System.out.println("File not found!");
                        }
                    }
                }
                case SAVE_AS -> System.out.println("I am SAVE_AS.\t\t" + Command.SAVE_AS.getDescription());
                case HELP -> {
                    for (Command commandValue : Command.values()) {
                        System.out.printf("%-20s%s\n", commandValue.getCommand().toUpperCase(), commandValue.getDescription());
                    }
                }
                case EXIT -> {
                    System.out.println("I am EXIT.\t\t" + Command.EXIT.getDescription());
                    System.exit(0);
                }
                case LOGIN -> {
                    if (openedFile == null) {
                        throw new FileNotFoundException("The file doesn't exist!");
                    }
                    if (!openedFile.getName().equals("users.xml")) {
                        System.out.println("Cannot perform user operation while working on books file!");
                        break;
                    }
                    System.out.println("I am LOGIN.\t\t" + Command.LOGIN.getDescription());
                }
                case LOGOUT -> {
                    if (openedFile == null) {
                        throw new FileNotFoundException("The file doesn't exist!");
                    }
                    if (!openedFile.getName().equals("users.xml")) {
                        System.out.println("Cannot perform user operation while working on books file!");
                        break;
                    }
                    System.out.println("I am LOGOUT.\t\t" + Command.LOGOUT.getDescription());
                }
                //will fix this in the future
                case BOOKS_ALL -> {
                    ReadBooks reader = new ReadBooks();
                    reader.read();
                    System.out.printf("%-10s%-15s%-15s%-40s%-20s%-30s%-15s%-15s\n",
                            "author",
                            "title",
                            "genre",
                            "description",
                            "year published",
                            "key words",
                            "rating",
                            "isbn");
                    for (Book book : BookList.getInstance().getBookList()) {
                        System.out.printf("%-10s%-15s%-15s%-40s%-20s%-30s%-15s%-15s\n",
                                book.getAuthor().toString(),
                                book.getTitle(),
                                book.getGenre().getText(),
                                book.getDescription(),
                                book.getPublishingYear(),
                                book.getKeyWords(),
                                book.getRating().getText(),
                                book.getIsbn());
                    }
                }
                case BOOKS_ADD -> {
//                    Book test = new Book.Builder(new Author("Ivan", "Georgiev", "Bulgariq"),
//                            "Probna kniga",
//                            2024,
//                            "123456789")
//                            .keyWords("probni dumi, proba, 123")
//                            .genre(Genre.CHILDRENS)
//                            .rating(Rating.FIVE)
//                            .description("ne znam kakvo da napisha, placeholder i guess")
//                            .build();
//                    WriteBooks writer = new WriteBooks();
//                    BookList.getInstance().add(test);
//                    try {
//                        writer.writeFile(BookList.getInstance().getBookList());
//                    } catch (Exception ex) {
//                        System.out.println("prosto proba");
//                    }
//                    ReadBooks reader = new ReadBooks();
//                    reader.read();
//                    for (Book book : BookList.getInstance().getBookList()) {
//                        System.out.println(book.toString());
//                    }
                    if (openedFile == null) {
                        throw new FileNotFoundException("The file doesn't exist!");
                    }
                    if (!openedFile.getName().equals("books.xml")) {
                        System.out.println("Cannot perform book operation while working on users file!");
                        break;
                    }
                    System.out.println("I am BOOKS_ADD.\t\t" + Command.BOOKS_ADD.getDescription());
                }
                case BOOKS_INFO -> {
                    if (openedFile == null) {
                        throw new FileNotFoundException("The file doesn't exist!");
                    }
                    if (!openedFile.getName().equals("books.xml")) {
                        System.out.println("Cannot perform book operation while working on users file!");
                        break;
                    }
                    System.out.println("I am BOOKS_INFO.\t\t" + Command.BOOKS_INFO.getDescription());
                }
                case BOOKS_FIND -> {
                    if (openedFile == null) {
                        throw new FileNotFoundException("The file doesn't exist!");
                    }
                    if (!openedFile.getName().equals("books.xml")) {
                        System.out.println("Cannot perform book operation while working on users file!");
                        break;
                    }
                    System.out.println("I am BOOKS_FIND.\t\t" + Command.BOOKS_FIND.getDescription());
                }
                case BOOKS_SORT -> {
                    if (openedFile == null) {
                        throw new FileNotFoundException("The file doesn't exist!");
                    }
                    if (!openedFile.getName().equals("books.xml")) {
                        System.out.println("Cannot perform book operation while working on users file!");
                        break;
                    }
                    System.out.println("I am BOOKS_SORT.\t\t" + Command.BOOKS_SORT.getDescription());
                }
                case USERS_ADD -> {
                    if (openedFile == null) {
                        throw new FileNotFoundException("The file doesn't exist!");
                    }
                    if (!openedFile.getName().equals("users.xml")) {
                        System.out.println("Cannot perform user operation while working on books file!");
                        break;
                    }
                    System.out.println("I am USERS_ADD.\t\t" + Command.USERS_ADD.getDescription());
                }
                case USERS_REMOVE -> {
                    if (openedFile == null) {
                        throw new FileNotFoundException("The file doesn't exist!");
                    }
                    if (!openedFile.getName().equals("users.xml")) {
                        System.out.println("Cannot perform user operation while working on books file!");
                        break;
                    }
                    System.out.println("I am USERS_REMOVE.\t\t" + Command.USERS_REMOVE.getDescription());
                }
            }
        } catch (IllegalArgumentException ex) {
            System.out.println("Invalid command! Try again.");
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }
}
