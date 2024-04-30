package main.java.bg.tu_varna.sit.b1.f22621631.commands.user;

import main.java.bg.tu_varna.sit.b1.f22621631.commands.utility.data.AppData;
import main.java.bg.tu_varna.sit.b1.f22621631.contracts.controllers.RunnableCommand;
import main.java.bg.tu_varna.sit.b1.f22621631.exceptions.files.UserFileNotOpenedException;
import main.java.bg.tu_varna.sit.b1.f22621631.exceptions.files.WrongFileOpenedException;
import main.java.bg.tu_varna.sit.b1.f22621631.exceptions.models.users.InvalidUserException;
import main.java.bg.tu_varna.sit.b1.f22621631.lists.UserList;
import main.java.bg.tu_varna.sit.b1.f22621631.users.User;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public class DisplayUser implements RunnableCommand {
    @Override
    public void execute() throws IOException, ParserConfigurationException, TransformerException, SAXException {
        if (AppData.getInstance().getOpenedFile() == null) {
            throw new UserFileNotOpenedException("Cannot perform user operations without opening the file!");
        }

        if (AppData.getInstance().getOpenedFile().getName().equals("books.xml")) {
            throw new WrongFileOpenedException("Cannot perform user operations while working on books file!");
        }

        if (AppData.getInstance().getActiveUser() == null) {
            throw new InvalidUserException("Cannot display users without having been logged in!");
        }

        System.out.printf("%-20s%s\n\n", "User:", "Clearance:");
        for (User user : UserList.getInstance().getUserList()) {
            System.out.printf("%-20s%s\n", user.getUsername(), user.getPermissionLevel().getText());
        }
    }
}
