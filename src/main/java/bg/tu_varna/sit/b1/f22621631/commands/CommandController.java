package main.java.bg.tu_varna.sit.b1.f22621631.commands;

import main.java.bg.tu_varna.sit.b1.f22621631.commands.read.ReadBooks;
import main.java.bg.tu_varna.sit.b1.f22621631.commands.write.WriteBooks;
import main.java.bg.tu_varna.sit.b1.f22621631.lists.BookList;
import main.java.bg.tu_varna.sit.b1.f22621631.lists.UserList;
import main.java.bg.tu_varna.sit.b1.f22621631.models.authors.Author;
import main.java.bg.tu_varna.sit.b1.f22621631.models.books.Book;
import main.java.bg.tu_varna.sit.b1.f22621631.models.books.enums.Genre;
import main.java.bg.tu_varna.sit.b1.f22621631.models.books.enums.Rating;

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
                }
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
