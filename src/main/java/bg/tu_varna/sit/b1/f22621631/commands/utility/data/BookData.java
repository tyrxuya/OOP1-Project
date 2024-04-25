package main.java.bg.tu_varna.sit.b1.f22621631.commands.utility.data;

import main.java.bg.tu_varna.sit.b1.f22621631.commands.utility.read.BookReader;
import main.java.bg.tu_varna.sit.b1.f22621631.commands.utility.write.BookWriter;
import main.java.bg.tu_varna.sit.b1.f22621631.contracts.controllers.RunnableCommand;
import main.java.bg.tu_varna.sit.b1.f22621631.contracts.data.AppDataManager;
import main.java.bg.tu_varna.sit.b1.f22621631.lists.BookList;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileNotFoundException;

public class BookData implements AppDataManager {
    @Override
    public void load() throws Exception {
        RunnableCommand bookReader = new BookReader();
        if (BookList.getInstance().getBookList().isEmpty()) {
            unload();
        }
        bookReader.execute();
    }

    @Override
    public void unload() throws Exception {
        RunnableCommand bookWriter = new BookWriter();
        bookWriter.execute();
    }
}
