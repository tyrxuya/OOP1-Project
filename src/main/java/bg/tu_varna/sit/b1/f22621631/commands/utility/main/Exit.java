package main.java.bg.tu_varna.sit.b1.f22621631.commands.utility.main;

import main.java.bg.tu_varna.sit.b1.f22621631.contracts.controllers.RunnableCommand;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public class Exit implements RunnableCommand {
    @Override
    public void execute() throws IOException, ParserConfigurationException, TransformerException, SAXException {
//        if (AppData.getInstance().getOpenedFile() != null) {
//            AppData.getInstance().unload();
//        }
        System.out.println("Exiting...");
        System.exit(0);
    }
}
