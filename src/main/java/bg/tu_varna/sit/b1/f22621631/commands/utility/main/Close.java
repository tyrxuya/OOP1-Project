package main.java.bg.tu_varna.sit.b1.f22621631.commands.utility.main;

import main.java.bg.tu_varna.sit.b1.f22621631.commands.utility.data.AppData;
import main.java.bg.tu_varna.sit.b1.f22621631.contracts.controllers.RunnableCommand;
import main.java.bg.tu_varna.sit.b1.f22621631.exceptions.files.FileNotOpenedException;
import main.java.bg.tu_varna.sit.b1.f22621631.lists.BookList;
import main.java.bg.tu_varna.sit.b1.f22621631.lists.UserList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.Objects;

public class Close implements RunnableCommand {
    @Override
    public void execute() throws IOException, ParserConfigurationException, TransformerException, SAXException {
        //AppData.getInstance().unload();
        if (Objects.isNull(AppData.getInstance().getOpenedFile())) {
            throw new FileNotOpenedException("No file open to close!");
        }

        if (AppData.getInstance().getOpenedFile().getName().equals("books.xml")) {
            BookList.getInstance().clear();
        }

        if (AppData.getInstance().getOpenedFile().getName().equals("users.xml")) {
            UserList.getInstance().clear();
        }

        AppData.getInstance().setOpenedFile(null);
        System.out.println("Closed!");
    }
}
