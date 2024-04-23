package main.java.bg.tu_varna.sit.b1.f22621631.commands;

import main.java.bg.tu_varna.sit.b1.f22621631.commands.factories.CommandFactory;
import main.java.bg.tu_varna.sit.b1.f22621631.commands.utility.main.Exit;
import main.java.bg.tu_varna.sit.b1.f22621631.commands.utility.main.Help;
import main.java.bg.tu_varna.sit.b1.f22621631.commands.utility.main.Open;
import main.java.bg.tu_varna.sit.b1.f22621631.contracts.controllers.RunnableCommand;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandController {
    private static Map<Command, CommandFactory> commands = new HashMap<>();

    static {
        commands.put(Command.OPEN, CommandFactory.getInstance());
        commands.put(Command.EXIT, CommandFactory.getInstance());
        commands.put(Command.CLOSE, CommandFactory.getInstance());
        commands.put(Command.SAVE, CommandFactory.getInstance());
        commands.put(Command.SAVE_AS, CommandFactory.getInstance());
        commands.put(Command.HELP, CommandFactory.getInstance());

        commands.put(Command.BOOKS_ALL, CommandFactory.getInstance());
        commands.put(Command.BOOKS_FIND, CommandFactory.getInstance());
        commands.put(Command.BOOKS_ADD, CommandFactory.getInstance());
        commands.put(Command.BOOKS_REMOVE, CommandFactory.getInstance());
        commands.put(Command.BOOKS_SORT, CommandFactory.getInstance());
        commands.put(Command.BOOKS_INFO, CommandFactory.getInstance());

        commands.put(Command.LOGIN, CommandFactory.getInstance());
        commands.put(Command.LOGOUT, CommandFactory.getInstance());
        commands.put(Command.USERS_ADD, CommandFactory.getInstance());
        commands.put(Command.USERS_REMOVE, CommandFactory.getInstance());
    }

    public static void run(String command) {
        List<String> arguments = Arrays.stream(command.split(" ")).toList();
        command = parseInput(command);
        int startingIndex = (command.startsWith("BOOKS") || command.startsWith("USERS") ? 2 : 1);
        arguments = arguments.subList(startingIndex, arguments.size());
        //Here we will call the functionalities later when they are developed. For now, it's used for printing purposes
        try {
//            commands.get(Command.valueOf(command)).getCommand(Command.valueOf(command), arguments).execute();
            CommandFactory.getInstance().getCommand(Command.valueOf(command), arguments).execute();
//            switch (Command.valueOf(firstArg.toUpperCase())) {
//                case OPEN -> {
//                    if (commandArgs.length == 1) {
//                        System.out.println(Command.valueOf(firstArg.toUpperCase()).getDescription());
//                        break;
//                    }
//                    openedFile = utilityController.open(commandArgs[1]);
//                    if (openedFile.getName().equals("books.xml")) {
//                        ReadBooks books = new ReadBooks();
//                        books.read();
//                    }
//                    if (openedFile.getName().equals("users.xml")) {
//                        ReadUsers users = new ReadUsers();
//                        users.read();
//                    }
//                    System.out.println("File " + openedFile.getName() + " opened successfully!");
//                }
//                case CLOSE -> {
//                    System.out.println("File " + openedFile.getName() + " closed successfully!");
//                    if (openedFile.getName().equals("books.xml")) {
//                        WriteBooks books = new WriteBooks();
//                        books.writeFile(BookList.getInstance().getBookList());
//                    }
//                    if (openedFile.getName().equals("users.xml")) {
//                        WriteUsers users = new WriteUsers();
//                        users.writeFile(UserList.getInstance().getUserList());
//                    }
//                    openedFile = null;
//                }
//                case SAVE -> {
//                    utilityController.save();
//                }
//                case SAVE_AS -> {
//                    if (commandArgs.length == 1) {
//                        System.out.println(Command.valueOf(firstArg.toUpperCase()).getDescription());
//                        break;
//                    }
//                    utilityController.saveAs(commandArgs[2]);
//                }
//                case HELP -> {
//                    utilityController.help();
//                }
//                case EXIT -> {
//                    utilityController.exit();
//                }
//                case LOGIN -> {
//                    if (openedFile == null) {
//                        throw new FileNotFoundException("The file doesn't exist!");
//                    }
//                    if (!openedFile.getName().equals("users.xml")) {
//                        System.out.println("Cannot perform user operation while working on books file!");
//                        break;
//                    }
//                    System.out.println("I am LOGIN.\t\t" + Command.LOGIN.getDescription());
//                }
//                case LOGOUT -> {
//                    if (openedFile == null) {
//                        throw new FileNotFoundException("The file doesn't exist!");
//                    }
//                    if (!openedFile.getName().equals("users.xml")) {
//                        System.out.println("Cannot perform user operation while working on books file!");
//                        break;
//                    }
//                    System.out.println("I am LOGOUT.\t\t" + Command.LOGOUT.getDescription());
//                }
//                //will fix this in the future
//                case BOOKS_ALL -> {
//                    bookController.all();
//                }
//                case BOOKS_ADD -> {
//                    if (openedFile == null) {
//                        throw new FileNotFoundException("The file doesn't exist!");
//                    }
//                    if (!openedFile.getName().equals("books.xml")) {
//                        System.out.println("Cannot perform book operation while working on users file!");
//                        break;
//                    }
//                    bookController.add();
//                }
//                case BOOKS_INFO -> {
//                    if (openedFile == null) {
//                        throw new FileNotFoundException("The file doesn't exist!");
//                    }
//                    if (!openedFile.getName().equals("books.xml")) {
//                        System.out.println("Cannot perform book operation while working on users file!");
//                        break;
//                    }
//                    if (commandArgs.length == 2) {
//                        System.out.println(Command.valueOf(firstArg.toUpperCase()).getDescription());
//                        break;
//                    }
//                    bookController.info(commandArgs[2]);
//                }
//                case BOOKS_FIND -> {
//                    if (openedFile == null) {
//                        throw new FileNotFoundException("The file doesn't exist!");
//                    }
//                    if (!openedFile.getName().equals("books.xml")) {
//                        System.out.println("Cannot perform book operation while working on users file!");
//                        break;
//                    }
//                    if (commandArgs.length == 2) {
//                        System.out.println(Command.valueOf(firstArg.toUpperCase()).getDescription());
//                        break;
//                    }
//                    bookController.find(commandArgs[2], commandArgs[3]);
//                }
//                case BOOKS_SORT -> {
//                    if (openedFile == null) {
//                        throw new FileNotFoundException("The file doesn't exist!");
//                    }
//                    if (!openedFile.getName().equals("books.xml")) {
//                        System.out.println("Cannot perform book operation while working on users file!");
//                        break;
//                    }
//                    if (commandArgs.length == 2) {
//                        System.out.println(Command.valueOf(firstArg.toUpperCase()).getDescription());
//                        break;
//                    }
//                    if (commandArgs.length == 3) {
//                        bookController.sort(commandArgs[2], true);
//                    }
//                    else {
//                        bookController.sort(commandArgs[2], commandArgs[3].equalsIgnoreCase("asc"));
//                    }
//                }
//                case USERS_ADD -> {
//                    if (openedFile == null) {
//                        throw new FileNotFoundException("The file doesn't exist!");
//                    }
//                    if (!openedFile.getName().equals("users.xml")) {
//                        System.out.println("Cannot perform user operation while working on books file!");
//                        break;
//                    }
//                    System.out.println("I am USERS_ADD.\t\t" + Command.USERS_ADD.getDescription());
//                }
//                case USERS_REMOVE -> {
//                    if (openedFile == null) {
//                        throw new FileNotFoundException("The file doesn't exist!");
//                    }
//                    if (!openedFile.getName().equals("users.xml")) {
//                        System.out.println("Cannot perform user operation while working on books file!");
//                        break;
//                    }
//                    System.out.println("I am USERS_REMOVE.\t\t" + Command.USERS_REMOVE.getDescription());
//                }
//            }
        } catch (IllegalArgumentException ex) {
            System.out.println("Invalid command! Try again.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static String parseInput(String input) {
        if (input.isBlank()) {
            return null;
        }

        String[] arguments = input.split(" ");
        String firstArg = arguments[0].toLowerCase();

        switch (firstArg) {
            case "books", "users" -> {
                firstArg += "_" + arguments[1].toLowerCase();
            }
            case "save" -> {
                if (arguments.length == 1) {
                    break;
                }
                if (arguments[1].equals("as")) {
                    firstArg += "_" + arguments[1].toLowerCase();
                }
            }
        }

        return firstArg.toUpperCase();
    }
}
