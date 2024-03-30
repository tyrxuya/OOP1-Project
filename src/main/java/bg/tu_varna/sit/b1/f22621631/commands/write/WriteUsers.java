package main.java.bg.tu_varna.sit.b1.f22621631.commands.write;

import main.java.bg.tu_varna.sit.b1.f22621631.contracts.FileWritable;
import main.java.bg.tu_varna.sit.b1.f22621631.models.books.Book;
import main.java.bg.tu_varna.sit.b1.f22621631.users.User;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class WriteUsers extends GeneralWriter implements FileWritable {
    private final String FILE_NAME = ".\\src\\main\\java\\bg\\tu_varna\\sit\\b1\\f22621631\\files\\books.xml";
    private File file = new File(FILE_NAME);


    public void writeFile(List<User> users) throws ParserConfigurationException {
        writeXml(FILE_NAME, generateDocument(users));
    }

    @Override
    public void writeXml(String fileName, Document document) {
        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            write(document, new FileOutputStream(file));
        } catch (Exception ex) {
            System.out.println("Error in writing to books.xml!");
        }
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
