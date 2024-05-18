package main.java.bg.tu_varna.sit.b1.f22621631.commands.user;

import main.java.bg.tu_varna.sit.b1.f22621631.commands.utility.data.AppData;
import main.java.bg.tu_varna.sit.b1.f22621631.contracts.controllers.RunnableCommand;
import main.java.bg.tu_varna.sit.b1.f22621631.exceptions.commands.WrongSyntaxException;
import main.java.bg.tu_varna.sit.b1.f22621631.exceptions.files.UserFileNotOpenedException;
import main.java.bg.tu_varna.sit.b1.f22621631.exceptions.files.WrongFileOpenedException;
import main.java.bg.tu_varna.sit.b1.f22621631.exceptions.models.users.InvalidPermissionLevelException;
import main.java.bg.tu_varna.sit.b1.f22621631.exceptions.models.users.InvalidUserException;
import main.java.bg.tu_varna.sit.b1.f22621631.lists.UserList;

import java.util.List;

public class RemoveUser implements RunnableCommand {
    private final List<String> arguments;

    public RemoveUser(List<String> arguments) {
        this.arguments = arguments;
    }

    @Override
    public void execute() {
        if (AppData.getInstance().getOpenedFile() == null) {
            throw new UserFileNotOpenedException("Cannot perform user operations without opening the file!");
        }

        if (AppData.getInstance().getOpenedFile().getName().equals("books.xml")) {
            throw new WrongFileOpenedException("Cannot perform user operations while working on books file!");
        }

        if (AppData.getInstance().getActiveUser() == null) {
            throw new InvalidUserException("Cannot remove user without being logged in!");
        }

        if (AppData.getInstance().getActiveUser().getPermissionLevel().getText().equals("User")) {
            throw new InvalidPermissionLevelException("Access denied, ADMINISTRATOR permission required!");
        }

        if (arguments.size() != 1) {
            throw new WrongSyntaxException("Wrong syntax! Expected: users remove <username>");
        }

        UserList.getInstance().remove(arguments.get(0));
        System.out.println("User removed successfully!");
    }
}
