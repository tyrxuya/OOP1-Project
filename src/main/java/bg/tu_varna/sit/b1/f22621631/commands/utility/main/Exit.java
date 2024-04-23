package main.java.bg.tu_varna.sit.b1.f22621631.commands.utility.main;

import main.java.bg.tu_varna.sit.b1.f22621631.commands.utility.data.AppData;
import main.java.bg.tu_varna.sit.b1.f22621631.contracts.controllers.RunnableCommand;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileNotFoundException;

public class Exit implements RunnableCommand {
    @Override
    public void execute() throws Exception {
        if (AppData.getInstance().getOpenedFile() != null) {
            AppData.getInstance().unload();
        }
        System.exit(0);
    }
}
