package main.java.bg.tu_varna.sit.b1.f22621631.commands.utility.write;

import main.java.bg.tu_varna.sit.b1.f22621631.contracts.files.FileWritable;
import main.java.bg.tu_varna.sit.b1.f22621631.lists.UserList;
import main.java.bg.tu_varna.sit.b1.f22621631.users.User;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class UserWriter extends GeneralWriter implements FileWritable {
    private File file = new File(getDEFAULT_PATH().concat("users.xml"));

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    @Override
    public void execute() throws ParserConfigurationException, IOException, TransformerException {
        writeFile(UserList.getInstance().getUserList());
        setFile(new File(getDEFAULT_PATH().concat("users.xml")));
    }

    public void writeFile(List<User> users) throws ParserConfigurationException, IOException, TransformerException {
        writeXml(generateDocument(users));
    }

    @Override
    public void writeXml(Document document) throws IOException, TransformerException {
        if (!file.exists()) {
            file.createNewFile();
        }

        write(document, new FileOutputStream(file));
    }

    private Document generateDocument(List<User> users) throws ParserConfigurationException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.newDocument();

        Element root = document.createElement("list");
        document.appendChild(root);

        for (User currentUser : users) {
            Element user = document.createElement("user");

            Element username = document.createElement("username");
            Text usernameText = document.createTextNode(currentUser.getUsername());
            username.appendChild(usernameText);

            Element password = document.createElement("password");
            Text passwordText = document.createTextNode(currentUser.getPassword());
            password.appendChild(passwordText);

            Element permissionLevel = document.createElement("permissionLevel");
            Text permissionLevelText = document.createTextNode(currentUser.getPermissionLevel().toString());
            permissionLevel.appendChild(permissionLevelText);

            user.appendChild(username);
            user.appendChild(password);
            user.appendChild(permissionLevel);

            root.appendChild(user);
        }

        return document;
    }
}
