package main.java.bg.tu_varna.sit.b1.f22621631.commands.controllers;

import main.java.bg.tu_varna.sit.b1.f22621631.commands.Command;
import main.java.bg.tu_varna.sit.b1.f22621631.commands.write.WriteBooks;
import main.java.bg.tu_varna.sit.b1.f22621631.commands.write.WriteUsers;
import main.java.bg.tu_varna.sit.b1.f22621631.contracts.controllers.UtilityOperations;
import main.java.bg.tu_varna.sit.b1.f22621631.lists.BookList;
import main.java.bg.tu_varna.sit.b1.f22621631.lists.UserList;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;

public class UtilityController implements UtilityOperations {
    private File openedFile = null;

    @Override
    public File open(String fileName) throws FileNotFoundException {
        if (!(fileName.equals("books.xml") || fileName.equals("users.xml"))) {
            throw new FileNotFoundException("The file doesn't exist!");
        }
        openedFile = new File(fileName);
        return openedFile;
    }

    @Override
    public void save() throws FileNotFoundException, ParserConfigurationException {
        if (openedFile == null) {
            throw new FileNotFoundException("The file doesn't exist!");
        }
        switch (openedFile.getName()) {
            case "books.xml" -> {
                WriteBooks booksWriter = new WriteBooks();
                booksWriter.writeFile(BookList.getInstance().getBookList());
                System.out.println("File " + openedFile.getName() + " saved successfully!");
            }
            case "users.xml" -> {
                WriteUsers usersWriter = new WriteUsers();
                usersWriter.writeFile(UserList.getInstance().getUserList());
                System.out.println("File " + openedFile.getName() + " saved successfully!");
            }
            default -> {
                System.out.println("File not found!");
            }
        }
    }

    @Override
    public void saveAs(String newFileName) throws FileNotFoundException, ParserConfigurationException {
        if (openedFile == null) {
            throw new FileNotFoundException("The file doesn't exist!");
        }
        switch (openedFile.getName()) {
            case "books.xml" -> {
                WriteBooks booksWriter = new WriteBooks();
                booksWriter.setFile(new File(booksWriter.getFILE_PATH().concat(newFileName)));
                booksWriter.writeFile(BookList.getInstance().getBookList());
                System.out.println("File " + newFileName + " saved successfully!");
            }
            case "users.xml" -> {
                WriteUsers usersWriter = new WriteUsers();
                usersWriter.setFile(new File(usersWriter.getFILE_PATH().concat(newFileName)));
                usersWriter.writeFile(UserList.getInstance().getUserList());
                System.out.println("File " + newFileName + " saved successfully!");
            }
            default -> {
                System.out.println("File not found!");
            }
        }
    }

    @Override
    public void help() {
        for (Command commandValue : Command.values()) {
            System.out.printf("%-20s%s\n", commandValue.getCommand().toUpperCase(), commandValue.getDescription());
        }
    }

    @Override
    public void exit() {
        System.out.println("I am EXIT.\t\t" + Command.EXIT.getDescription());
        System.exit(0);
    }
}
