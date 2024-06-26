package main.java.bg.tu_varna.sit.b1.f22621631.commands.utility.main;

import main.java.bg.tu_varna.sit.b1.f22621631.commands.utility.data.AppData;
import main.java.bg.tu_varna.sit.b1.f22621631.commands.utility.write.BookInitializer;
import main.java.bg.tu_varna.sit.b1.f22621631.commands.utility.write.UserInitializer;
import main.java.bg.tu_varna.sit.b1.f22621631.contracts.controllers.RunnableCommand;
import main.java.bg.tu_varna.sit.b1.f22621631.exceptions.commands.WrongSyntaxException;
import main.java.bg.tu_varna.sit.b1.f22621631.exceptions.files.FileAlreadyOpenedException;
import main.java.bg.tu_varna.sit.b1.f22621631.exceptions.files.WrongFileOpenedException;
import main.java.bg.tu_varna.sit.b1.f22621631.lists.BookList;
import main.java.bg.tu_varna.sit.b1.f22621631.lists.UserList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class Open implements RunnableCommand {
    private final List<String> arguments;
    private final String PATH = ".\\src\\main\\java\\bg\\tu_varna\\sit\\b1\\f22621631\\files\\";

    public Open(List<String> arguments) {
        this.arguments = arguments;
    }

    @Override
    public void execute() throws IOException, ParserConfigurationException, TransformerException, SAXException {
        if (arguments.size() != 1) {
            throw new WrongSyntaxException("Wrong syntax: open <fileName>");
        }

        if (!arguments.get(0).equals("books.xml") && !arguments.get(0).equals("users.xml")) {
            throw new WrongFileOpenedException("No file with such name found!");
        }

        if (Objects.nonNull(AppData.getInstance().getOpenedFile())) {
            throw new FileAlreadyOpenedException("A file is already opened!");
        }

        //AppData.getInstance().load(new File(PATH.concat(argument.get(0))));
        File file = new File(PATH.concat(arguments.get(0)));
        if (!file.exists()) {
            file.createNewFile();
        }
        AppData.getInstance().setOpenedFile(file);

        AppData.getInstance().load(AppData.getInstance().getOpenedFile());

        if (file.getName().equals("users.xml") && UserList.getInstance().getUserList().isEmpty()) {
            (new UserInitializer()).execute();
        }

        if (file.getName().equals("books.xml") && BookList.getInstance().getBookList().isEmpty()) {
            (new BookInitializer()).execute();
        }

        System.out.println("File " + arguments.get(0) + " opened successfully!");
    }
}
