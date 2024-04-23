package main.java.bg.tu_varna.sit.b1.f22621631.commands.utility.data;

import main.java.bg.tu_varna.sit.b1.f22621631.commands.utility.write.BookWriter;
import main.java.bg.tu_varna.sit.b1.f22621631.commands.utility.write.UserWriter;
import main.java.bg.tu_varna.sit.b1.f22621631.contracts.data.AppDataManager;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;

public class AppData {
    private static AppData instance = null;
    private File openedFile = null;
    private AppDataManager appDataManager;

    private AppData() {}

    public static AppData getInstance() {
        if (instance == null) {
            instance = new AppData();
        }
        return instance;
    }

    public File getOpenedFile() {
        return openedFile;
    }

    public void load(File file) throws Exception {
        this.openedFile = file;
        updateAppDataManager();
        appDataManager.load();
    }

    public void unload() throws Exception {
        appDataManager.unload();
        this.openedFile = null;
        appDataManager = null;
    }

    public void save(File file) throws FileNotFoundException, ParserConfigurationException {
        switch (openedFile.getName()) {
            case "books.xml" -> {
                BookWriter bookWriter = new BookWriter();
                bookWriter.setFile(file);
                bookWriter.execute();
            }
            case "users.xml" -> {
                UserWriter userWriter = new UserWriter();
                userWriter.setFile(file);
                userWriter.execute();
            }
            default -> {
                throw new FileNotFoundException("File couldn't be found!");
            }
        }
    }

    public void updateAppDataManager() throws FileNotFoundException {
        if (openedFile == null) {
            throw new FileNotFoundException("File couldn't be found!");
        }
        if (openedFile.getName().equals("books.xml")) {
            appDataManager = new BookData();
        }
        if (openedFile.getName().equals("users.xml")) {
            appDataManager = new UserData();
        }
    }
}
