package main.java.bg.tu_varna.sit.b1.f22621631.commands.utility.main;

import main.java.bg.tu_varna.sit.b1.f22621631.commands.utility.data.AppData;
import main.java.bg.tu_varna.sit.b1.f22621631.contracts.controllers.RunnableCommand;
import main.java.bg.tu_varna.sit.b1.f22621631.exceptions.commands.WrongSyntaxException;
import main.java.bg.tu_varna.sit.b1.f22621631.exceptions.files.FileNotOpenedException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

public class SaveAs implements RunnableCommand {
    private final List<String> arguments;

    public SaveAs(List<String> arguments) {
        this.arguments = arguments;
    }

    @Override
    public void execute() throws ParserConfigurationException, IOException, TransformerException {
        if (Objects.isNull(AppData.getInstance().getOpenedFile())) {
            throw new FileNotOpenedException("No file open to save!");
        }

        if (arguments.size() != 1) {
            throw new WrongSyntaxException("Wrong syntax: save as <fileName>");
        }

        File fileToSave = new File(
                arguments.get(0).contains(File.separator) ?
                        arguments.get(0) :
                        Paths.get(
                                AppData.getInstance().
                                        getOpenedFile().
                                        getPath()
                        ).
                                toAbsolutePath().
                                getParent().
                                toString().
                                concat(File.separator).
                                concat(arguments.get(0))
        );

        AppData.getInstance().save(fileToSave);
        System.out.println("File " + fileToSave.getName() + " saved successfully!");
    }
}
