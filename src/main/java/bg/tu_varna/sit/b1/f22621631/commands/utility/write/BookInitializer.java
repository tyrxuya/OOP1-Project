package main.java.bg.tu_varna.sit.b1.f22621631.commands.utility.write;

import main.java.bg.tu_varna.sit.b1.f22621631.contracts.controllers.RunnableCommand;
import main.java.bg.tu_varna.sit.b1.f22621631.lists.BookList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public class BookInitializer implements RunnableCommand {
    private BookWriter bookWriter = new BookWriter();

    @Override
    public void execute() throws IOException, ParserConfigurationException, TransformerException, SAXException {
        bookWriter.writeFile(BookList.getInstance().getBookList());
    }
}
