package main.java.bg.tu_varna.sit.b1.f22621631.commands.utility.data;

import main.java.bg.tu_varna.sit.b1.f22621631.commands.utility.read.UserReader;
import main.java.bg.tu_varna.sit.b1.f22621631.commands.utility.write.UserWriter;
import main.java.bg.tu_varna.sit.b1.f22621631.contracts.controllers.RunnableCommand;
import main.java.bg.tu_varna.sit.b1.f22621631.contracts.data.AppDataManager;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileNotFoundException;

public class UserData implements AppDataManager {
    @Override
    public void load() throws Exception {
        RunnableCommand userReader = new UserReader();
        userReader.execute();
    }

    @Override
    public void unload() throws Exception {
        RunnableCommand userWriter = new UserWriter();
        userWriter.execute();
    }
}
