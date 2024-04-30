package main.java.bg.tu_varna.sit.b1.f22621631.commands.utility.main;

import main.java.bg.tu_varna.sit.b1.f22621631.commands.utility.data.AppData;
import main.java.bg.tu_varna.sit.b1.f22621631.contracts.controllers.RunnableCommand;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public class Close implements RunnableCommand {
    @Override
    public void execute() throws IOException, ParserConfigurationException, TransformerException, SAXException {
        //AppData.getInstance().unload();
        AppData.getInstance().setOpenedFile(null);
        System.out.println("Closed!");
    }
}
