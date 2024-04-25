package main.java.bg.tu_varna.sit.b1.f22621631.commands.utility.data;

import main.java.bg.tu_varna.sit.b1.f22621631.commands.utility.read.UserReader;
import main.java.bg.tu_varna.sit.b1.f22621631.commands.utility.write.UserWriter;
import main.java.bg.tu_varna.sit.b1.f22621631.contracts.controllers.RunnableCommand;
import main.java.bg.tu_varna.sit.b1.f22621631.contracts.data.AppDataManager;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.FileNotFoundException;
import java.io.IOException;

public class UserData implements AppDataManager {
    @Override
    public void load() throws IOException, ParserConfigurationException, TransformerException, SAXException {
        RunnableCommand userReader = new UserReader();
        userReader.execute();
    }

    @Override
    public void unload() throws IOException, ParserConfigurationException, TransformerException, SAXException {
        RunnableCommand userWriter = new UserWriter();
        userWriter.execute();
    }
}
