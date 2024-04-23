package main.java.bg.tu_varna.sit.b1.f22621631.commands.utility.main;

import main.java.bg.tu_varna.sit.b1.f22621631.commands.utility.data.AppData;
import main.java.bg.tu_varna.sit.b1.f22621631.contracts.controllers.RunnableCommand;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileNotFoundException;

public class Save implements RunnableCommand {
    @Override
    public void execute() throws Exception {
        AppData.getInstance().unload();
        System.out.println("Saved!");
    }
}
