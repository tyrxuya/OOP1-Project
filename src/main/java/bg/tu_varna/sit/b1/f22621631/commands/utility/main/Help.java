package main.java.bg.tu_varna.sit.b1.f22621631.commands.utility.main;

import main.java.bg.tu_varna.sit.b1.f22621631.commands.Command;
import main.java.bg.tu_varna.sit.b1.f22621631.commands.utility.data.AppData;
import main.java.bg.tu_varna.sit.b1.f22621631.contracts.controllers.RunnableCommand;
import main.java.bg.tu_varna.sit.b1.f22621631.users.PermissionLevel;
import main.java.bg.tu_varna.sit.b1.f22621631.users.User;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileNotFoundException;
import java.util.Objects;

public class Help implements RunnableCommand {
    @Override
    public void execute() {
        User activeUser = AppData.getInstance().getActiveUser();
        PermissionLevel permissionLevel = Objects.isNull(activeUser) ? PermissionLevel.NONE : activeUser.getPermissionLevel();
        for (Command commandValue : Command.values()) {
            if (commandValue.getPermissionLevel().compareTo(permissionLevel) <= 0) {
                System.out.printf("%-20s%s\n", commandValue.getCommand().toUpperCase(), commandValue.getDescription());
            }
        }
    }
}
