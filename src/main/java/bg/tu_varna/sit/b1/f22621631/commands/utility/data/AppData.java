package main.java.bg.tu_varna.sit.b1.f22621631.commands.utility.data;

import main.java.bg.tu_varna.sit.b1.f22621631.commands.utility.write.BookWriter;
import main.java.bg.tu_varna.sit.b1.f22621631.commands.utility.write.UserWriter;
import main.java.bg.tu_varna.sit.b1.f22621631.contracts.data.AppDataManager;
import main.java.bg.tu_varna.sit.b1.f22621631.lists.UserList;
import main.java.bg.tu_varna.sit.b1.f22621631.users.PermissionLevel;
import main.java.bg.tu_varna.sit.b1.f22621631.users.User;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;

public class AppData {
    private static AppData instance = null;
    private File openedFile = null;
    private User activeUser = new User("admin", "i<3c++", PermissionLevel.ADMINISTRATOR);
    private AppDataManager appDataManager;

    private AppData() throws Exception {}

    public static AppData getInstance() throws Exception {
        if (instance == null) {
            instance = new AppData();
        }
        return instance;
    }

    public File getOpenedFile() {
        return openedFile;
    }

    public void setOpenedFile(File openedFile) {
        this.openedFile = openedFile;
    }

    public void load(File file) throws Exception {
        if (!file.getName().equals("books.xml") && !file.getName().equals("users.xml")) {
            throw new FileNotFoundException("File couldn't be found!");
        }

        this.openedFile = file;
        updateAppDataManager();
        appDataManager.load();
    }

    public void unload() throws Exception {
        if (Objects.isNull(appDataManager)) {
            throw new Exception("No file open to save!");
        }

        appDataManager.unload();
        this.openedFile = null;
        appDataManager = null;
    }

    public void save(File file) throws Exception {
        if (Objects.isNull(openedFile)) {
            throw new Exception("No file open to save!");
        }

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

    public User getActiveUser() {
        return activeUser;
    }

    public void setActiveUser(User activeUser) {
        this.activeUser = activeUser;
    }

    public void updateAppDataManager() {
        if (openedFile.getName().equals("books.xml")) {
            appDataManager = new BookData();
        }
        if (openedFile.getName().equals("users.xml")) {
            appDataManager = new UserData();
        }
    }
}
