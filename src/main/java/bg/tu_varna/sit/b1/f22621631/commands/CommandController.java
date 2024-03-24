package main.java.bg.tu_varna.sit.b1.f22621631.commands;

public class CommandController {
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
                case OPEN -> System.out.println("I am OPEN.\t\t" + Command.OPEN.getDescription());
                case CLOSE -> System.out.println("I am CLOSE.\t\t" + Command.CLOSE.getDescription());
                case SAVE -> System.out.println("I am SAVE.\t\t" + Command.SAVE.getDescription());
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
                case LOGIN -> System.out.println("I am LOGIN.\t\t" + Command.LOGIN.getDescription());
                case LOGOUT -> System.out.println("I am LOGOUT.\t\t" + Command.LOGOUT.getDescription());
                case BOOKS_ALL -> System.out.println("I am BOOKS_ALL.\t\t" + Command.BOOKS_ALL.getDescription());
                case BOOKS_INFO -> System.out.println("I am BOOKS_INFO.\t\t" + Command.BOOKS_INFO.getDescription());
                case BOOKS_FIND -> System.out.println("I am BOOKS_FIND.\t\t" + Command.BOOKS_FIND.getDescription());
                case BOOKS_SORT -> System.out.println("I am BOOKS_SORT.\t\t" + Command.BOOKS_SORT.getDescription());
                case USERS_ADD -> System.out.println("I am USERS_ADD.\t\t" + Command.USERS_ADD.getDescription());
                case USERS_REMOVE -> System.out.println("I am USERS_REMOVE.\t\t" + Command.USERS_REMOVE.getDescription());
            }
        } catch (IllegalArgumentException ex) {
            System.out.println("Invalid command! Try again.");
        }
    }
}
