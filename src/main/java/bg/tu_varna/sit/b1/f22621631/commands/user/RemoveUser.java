package main.java.bg.tu_varna.sit.b1.f22621631.commands.user;

import main.java.bg.tu_varna.sit.b1.f22621631.commands.utility.data.AppData;
import main.java.bg.tu_varna.sit.b1.f22621631.contracts.controllers.RunnableCommand;
import main.java.bg.tu_varna.sit.b1.f22621631.lists.UserList;

import java.io.FileNotFoundException;
import java.util.List;

public class RemoveUser implements RunnableCommand {
    private List<String> argument;

    public RemoveUser(List<String> argument) {
        this.argument = argument;
    }

    @Override
    public void execute() throws Exception {
        if (AppData.getInstance().getOpenedFile() == null) {
            throw new FileNotFoundException("Cannot perform user operations without opening the file!");
        }

        if (AppData.getInstance().getOpenedFile().getName().equals("books.xml")) {
            throw new Exception("Cannot perform user operations while working on books file!");
        }

        if (AppData.getInstance().getActiveUser() == null) {
            throw new Exception("Cannot remove user without being logged in!");
        }

        if (AppData.getInstance().getActiveUser().getPermissionLevel().getText().equals("User")) {
            throw new Exception("Access denied, ADMINISTRATOR permission required!");
        }

        UserList.getInstance().remove(argument.get(0));
    }
}
