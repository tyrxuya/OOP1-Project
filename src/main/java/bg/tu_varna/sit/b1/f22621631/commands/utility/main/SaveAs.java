package main.java.bg.tu_varna.sit.b1.f22621631.commands.utility.main;

import main.java.bg.tu_varna.sit.b1.f22621631.commands.Command;
import main.java.bg.tu_varna.sit.b1.f22621631.commands.utility.data.AppData;
import main.java.bg.tu_varna.sit.b1.f22621631.contracts.controllers.RunnableCommand;
import main.java.bg.tu_varna.sit.b1.f22621631.exceptions.commands.WrongSyntaxException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class SaveAs implements RunnableCommand {
    private List<String> argument;
    private final String PATH = ".\\src\\main\\java\\bg\\tu_varna\\sit\\b1\\f22621631\\files\\";

    public SaveAs(List<String> argument) {
        this.argument = argument;
    }

    @Override
    public void execute() throws ParserConfigurationException, IOException, TransformerException {
        if (argument.isEmpty()) {
            throw new WrongSyntaxException("Wrong syntax: SAVE AS <fileName>");
        }

        AppData.getInstance().save(new File(PATH.concat(argument.get(0))));
        System.out.println("File " + argument.get(0) + " saved successfully!");
    }
}
