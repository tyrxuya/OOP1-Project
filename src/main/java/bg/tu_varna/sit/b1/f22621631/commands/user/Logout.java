package main.java.bg.tu_varna.sit.b1.f22621631.commands.user;

import main.java.bg.tu_varna.sit.b1.f22621631.commands.utility.data.AppData;
import main.java.bg.tu_varna.sit.b1.f22621631.contracts.controllers.RunnableCommand;
import main.java.bg.tu_varna.sit.b1.f22621631.exceptions.models.users.InvalidUserException;

public class Logout implements RunnableCommand {
    @Override
    public void execute() {
        if (AppData.getInstance().getActiveUser() == null) {
            throw new InvalidUserException("Cannot logout without having been logged in!");
        }

        AppData.getInstance().setActiveUser(null);
    }
}
