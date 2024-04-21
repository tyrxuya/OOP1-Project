package main.java.bg.tu_varna.sit.b1.f22621631.commands;

import main.java.bg.tu_varna.sit.b1.f22621631.commands.controllers.BookController;
import main.java.bg.tu_varna.sit.b1.f22621631.commands.controllers.UtilityController;
import main.java.bg.tu_varna.sit.b1.f22621631.commands.read.ReadBooks;
import main.java.bg.tu_varna.sit.b1.f22621631.commands.read.ReadUsers;
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
import java.util.Scanner;

public class CommandController {
    private static File openedFile = null;
    private static final UtilityController utilityController = new UtilityController();
    private static final BookController bookController = new BookController();

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
                    if (commandArgs.length == 1) {
                        System.out.println(Command.valueOf(firstArg.toUpperCase()).getDescription());
                        break;
                    }
                    openedFile = utilityController.open(commandArgs[1]);
                    if (openedFile.getName().equals("books.xml")) {
                        ReadBooks books = new ReadBooks();
                        books.read();
                    }
                    if (openedFile.getName().equals("users.xml")) {
                        ReadUsers users = new ReadUsers();
                        users.read();
                    }
                    System.out.println("File " + openedFile.getName() + " opened successfully!");
                }
                case CLOSE -> {
                    System.out.println("File " + openedFile.getName() + " closed successfully!");
                    if (openedFile.getName().equals("books.xml")) {
                        WriteBooks books = new WriteBooks();
                        books.writeFile(BookList.getInstance().getBookList());
                    }
                    if (openedFile.getName().equals("users.xml")) {
                        WriteUsers users = new WriteUsers();
                        users.writeFile(UserList.getInstance().getUserList());
                    }
                    openedFile = null;
                }
                case SAVE -> {
                    utilityController.save();
                }
                case SAVE_AS -> {
                    if (commandArgs.length == 1) {
                        System.out.println(Command.valueOf(firstArg.toUpperCase()).getDescription());
                        break;
                    }
                    utilityController.saveAs(commandArgs[2]);
                }
                case HELP -> {
                    utilityController.help();
                }
                case EXIT -> {
                    utilityController.exit();
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
                    bookController.all();
                }
                case BOOKS_ADD -> {
                    if (openedFile == null) {
                        throw new FileNotFoundException("The file doesn't exist!");
                    }
                    if (!openedFile.getName().equals("books.xml")) {
                        System.out.println("Cannot perform book operation while working on users file!");
                        break;
                    }
                    bookController.add();
                }
                case BOOKS_INFO -> {
                    if (openedFile == null) {
                        throw new FileNotFoundException("The file doesn't exist!");
                    }
                    if (!openedFile.getName().equals("books.xml")) {
                        System.out.println("Cannot perform book operation while working on users file!");
                        break;
                    }
                    if (commandArgs.length == 2) {
                        System.out.println(Command.valueOf(firstArg.toUpperCase()).getDescription());
                        break;
                    }
                    bookController.info(commandArgs[2]);
                }
                case BOOKS_FIND -> {
                    if (openedFile == null) {
                        throw new FileNotFoundException("The file doesn't exist!");
                    }
                    if (!openedFile.getName().equals("books.xml")) {
                        System.out.println("Cannot perform book operation while working on users file!");
                        break;
                    }
                    if (commandArgs.length == 2) {
                        System.out.println(Command.valueOf(firstArg.toUpperCase()).getDescription());
                        break;
                    }
                    bookController.find(commandArgs[2], commandArgs[3]);
                }
                case BOOKS_SORT -> {
                    if (openedFile == null) {
                        throw new FileNotFoundException("The file doesn't exist!");
                    }
                    if (!openedFile.getName().equals("books.xml")) {
                        System.out.println("Cannot perform book operation while working on users file!");
                        break;
                    }
                    if (commandArgs.length == 2) {
                        System.out.println(Command.valueOf(firstArg.toUpperCase()).getDescription());
                        break;
                    }
                    if (commandArgs.length == 3) {
                        bookController.sort(commandArgs[2], true);
                    }
                    else {
                        bookController.sort(commandArgs[2], commandArgs[3].equalsIgnoreCase("asc"));
                    }
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
        } catch (FileNotFoundException | ParserConfigurationException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
