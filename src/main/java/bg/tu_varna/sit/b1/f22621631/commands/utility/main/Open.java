package main.java.bg.tu_varna.sit.b1.f22621631.commands.utility.main;

import main.java.bg.tu_varna.sit.b1.f22621631.commands.utility.data.AppData;
import main.java.bg.tu_varna.sit.b1.f22621631.contracts.controllers.RunnableCommand;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public class Open implements RunnableCommand {
    private List<String> argument;
    private final String PATH = ".\\src\\main\\java\\bg\\tu_varna\\sit\\b1\\f22621631\\files\\";

    public Open(List<String> argument) {
        this.argument = argument;
    }

    @Override
    public void execute() throws Exception {
        AppData.getInstance().load(new File(PATH.concat(argument.get(0))));
        System.out.println("File " + argument.get(0) + " opened successfully!");
    }
}
