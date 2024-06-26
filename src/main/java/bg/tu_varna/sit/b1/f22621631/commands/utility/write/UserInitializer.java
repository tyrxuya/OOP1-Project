package main.java.bg.tu_varna.sit.b1.f22621631.commands.utility.write;

import main.java.bg.tu_varna.sit.b1.f22621631.contracts.controllers.RunnableCommand;
import main.java.bg.tu_varna.sit.b1.f22621631.lists.UserList;
import main.java.bg.tu_varna.sit.b1.f22621631.users.PermissionLevel;
import main.java.bg.tu_varna.sit.b1.f22621631.users.User;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public class UserInitializer implements RunnableCommand {
    private final UserWriter userWriter = new UserWriter();

    @Override
    public void execute() throws ParserConfigurationException, IOException, TransformerException {
        User initialUser = new User("admin", "i<3c++", PermissionLevel.ADMINISTRATOR);
        UserList.getInstance().add(initialUser);
        userWriter.writeFile(UserList.getInstance().getUserList());
    }
}
