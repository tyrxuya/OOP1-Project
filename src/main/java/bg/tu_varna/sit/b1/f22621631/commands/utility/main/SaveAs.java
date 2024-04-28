package main.java.bg.tu_varna.sit.b1.f22621631.commands.utility.main;

import main.java.bg.tu_varna.sit.b1.f22621631.commands.utility.data.AppData;
import main.java.bg.tu_varna.sit.b1.f22621631.contracts.controllers.RunnableCommand;
import main.java.bg.tu_varna.sit.b1.f22621631.exceptions.commands.WrongSyntaxException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class SaveAs implements RunnableCommand {
    private List<String> argument;

    public SaveAs(List<String> argument) {
        this.argument = argument;
    }

    @Override
    public void execute() throws ParserConfigurationException, IOException, TransformerException {
        if (argument.isEmpty()) {
            throw new WrongSyntaxException("Wrong syntax: SAVE AS <fileName>");
        }

        File fileToSave = new File(
                argument.get(0).contains(File.separator) ?
                        argument.get(0) :
                        Paths.get(
                                AppData.getInstance().
                                        getOpenedFile().
                                        getPath()
                        ).
                                toAbsolutePath().
                                getParent().
                                toString().
                                concat(File.separator).
                                concat(argument.get(0))
        );

        AppData.getInstance().save(fileToSave);
        System.out.println("File " + fileToSave.getName() + " saved successfully!");
    }
}
