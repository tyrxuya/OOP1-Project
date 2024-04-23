package main.java.bg.tu_varna.sit.b1.f22621631.commands.utility.main;

import main.java.bg.tu_varna.sit.b1.f22621631.commands.Command;
import main.java.bg.tu_varna.sit.b1.f22621631.contracts.controllers.RunnableCommand;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileNotFoundException;

public class Help implements RunnableCommand {
    @Override
    public void execute() throws ParserConfigurationException, FileNotFoundException {
        for (Command commandValue : Command.values()) {
            System.out.printf("%-20s%s\n", commandValue.getCommand().toUpperCase(), commandValue.getDescription());
        }
    }
}
