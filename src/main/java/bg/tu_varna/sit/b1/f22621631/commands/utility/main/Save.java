package main.java.bg.tu_varna.sit.b1.f22621631.commands.utility.main;

import main.java.bg.tu_varna.sit.b1.f22621631.commands.utility.data.AppData;
import main.java.bg.tu_varna.sit.b1.f22621631.contracts.controllers.RunnableCommand;
import main.java.bg.tu_varna.sit.b1.f22621631.exceptions.files.FileNotOpenedException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.Objects;

public class Save implements RunnableCommand {
    @Override
    public void execute() throws IOException, ParserConfigurationException, TransformerException, SAXException {
        if (Objects.isNull(AppData.getInstance().getOpenedFile())) {
            throw new FileNotOpenedException("No file to save!");
        }
        AppData.getInstance().unload();
        System.out.println("Saved!");
    }
}
