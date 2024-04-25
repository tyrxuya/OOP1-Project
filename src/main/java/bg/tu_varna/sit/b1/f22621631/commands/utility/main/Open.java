package main.java.bg.tu_varna.sit.b1.f22621631.commands.utility.main;

import main.java.bg.tu_varna.sit.b1.f22621631.commands.utility.data.AppData;
import main.java.bg.tu_varna.sit.b1.f22621631.commands.utility.write.UserInitializer;
import main.java.bg.tu_varna.sit.b1.f22621631.contracts.controllers.RunnableCommand;
import main.java.bg.tu_varna.sit.b1.f22621631.lists.UserList;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Objects;

public class Open implements RunnableCommand {
    private List<String> argument;
    private final String PATH = ".\\src\\main\\java\\bg\\tu_varna\\sit\\b1\\f22621631\\files\\";

    public Open(List<String> argument) {
        this.argument = argument;
    }

    @Override
    public void execute() throws Exception {
        if (argument.isEmpty()) {
            throw new Exception("Wrong syntax: OPEN <fileName>");
        }

        if (Objects.nonNull(AppData.getInstance().getOpenedFile())) {
            //FileAlreadyOpenException
        }

        //AppData.getInstance().load(new File(PATH.concat(argument.get(0))));
        File file = new File(PATH.concat(argument.get(0)));
        if (!file.exists()) {
            file.createNewFile();
        }
        AppData.getInstance().setOpenedFile(file);

        if (file.getName().equals("users.xml") && UserList.getInstance().getUserList().isEmpty()) {
            (new UserInitializer()).execute();
        }

        AppData.getInstance().load(AppData.getInstance().getOpenedFile());

        System.out.println("File " + argument.get(0) + " opened successfully!");
    }
}
