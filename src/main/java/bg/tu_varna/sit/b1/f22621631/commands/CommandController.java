package main.java.bg.tu_varna.sit.b1.f22621631.commands;

import main.java.bg.tu_varna.sit.b1.f22621631.commands.factories.CommandFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandController {
    private static final Map<Command, CommandFactory> commands = new HashMap<>();

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
        if (command == null) {
            return;
        }
        int startingIndex = (command.startsWith("BOOKS") || command.startsWith("USERS") || command.startsWith("SAVE_AS") ? 2 : 1);
        arguments = arguments.subList(startingIndex, arguments.size());
        try {
            //CommandFactory.getInstance().getCommand(Command.valueOf(command), arguments).execute();
            Command executableCommand = Command.valueOf(command);
            commands.get(executableCommand).getCommand(executableCommand, arguments).execute();
        } catch (IllegalArgumentException ex) {
            System.out.println("Invalid command! Try again.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static String parseInput(String input) {
        if (input.isBlank()) {
            return null; //enter sth idiot
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
