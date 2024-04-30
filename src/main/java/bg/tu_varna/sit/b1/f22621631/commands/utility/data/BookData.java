package main.java.bg.tu_varna.sit.b1.f22621631.commands.utility.data;

import main.java.bg.tu_varna.sit.b1.f22621631.commands.utility.read.BookReader;
import main.java.bg.tu_varna.sit.b1.f22621631.commands.utility.write.BookWriter;
import main.java.bg.tu_varna.sit.b1.f22621631.contracts.controllers.RunnableCommand;
import main.java.bg.tu_varna.sit.b1.f22621631.contracts.data.AppDataManager;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public class BookData implements AppDataManager {
    @Override
    public void load() throws IOException, ParserConfigurationException, TransformerException, SAXException {
        RunnableCommand bookReader = new BookReader();
//        if (BookList.getInstance().getBookList().isEmpty()) {
//            unload();
//        }
        bookReader.execute();
    }

    @Override
    public void unload() throws IOException, ParserConfigurationException, TransformerException, SAXException {
        RunnableCommand bookWriter = new BookWriter();
        bookWriter.execute();
    }
}
