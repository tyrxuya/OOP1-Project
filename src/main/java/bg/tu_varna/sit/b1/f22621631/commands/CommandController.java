package main.java.bg.tu_varna.sit.b1.f22621631.commands;

import main.java.bg.tu_varna.sit.b1.f22621631.commands.factories.CommandFactory;
import main.java.bg.tu_varna.sit.b1.f22621631.exceptions.commands.IllegalCommandException;
import main.java.bg.tu_varna.sit.b1.f22621631.exceptions.commands.WrongSyntaxException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandController {
    private static final Map<Command, CommandFactory> commands = new HashMap<>();

    static {
        for (Command command : Command.values()) {
            commands.put(command, CommandFactory.getInstance());
        } //!!!!
    }

    public static void run(String command) {
        List<String> arguments = Arrays.stream(command.split(" ")).toList();
        try {
            command = parseInput(command);

            if (command == null) {
                return;
            }

            if (!checkInput(command)) {
                throw new IllegalCommandException("Command doesn't exist! Type \"help\" for more information.");
            }

            int startingIndex = (command.startsWith("BOOKS") || command.startsWith("USERS") || command.startsWith("SAVE_AS") ? 2 : 1);
            arguments = arguments.subList(startingIndex, arguments.size());
            //CommandFactory.getInstance().getCommand(Command.valueOf(command), arguments).execute();
            Command executableCommand = Command.valueOf(command);
            commands.get(executableCommand).getCommand(executableCommand, arguments).execute();
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
                if (arguments.length == 1) {
                    throw new WrongSyntaxException("Wrong syntax! Type \"help\" for more information.");
                }
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

    private static Boolean checkInput(String input) {
        for (Command command : Command.values()) {
            String test = command.getCommand();
            if (input.contains("_")) {
                test = test.replace(" ", "_");
            }
            if (test.equalsIgnoreCase(input)) {
                return true;
            }
        }
        return false;
    }
}
