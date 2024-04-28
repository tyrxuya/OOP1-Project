package main.java.bg.tu_varna.sit.b1.f22621631.commands.utility.read;

import main.java.bg.tu_varna.sit.b1.f22621631.contracts.controllers.RunnableCommand;
import main.java.bg.tu_varna.sit.b1.f22621631.contracts.files.FileReadable;
import main.java.bg.tu_varna.sit.b1.f22621631.lists.UserList;
import main.java.bg.tu_varna.sit.b1.f22621631.users.PermissionLevel;
import main.java.bg.tu_varna.sit.b1.f22621631.users.User;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class UserReader implements FileReadable, RunnableCommand {
    private final File FILE_NAME = new File(".\\src\\main\\java\\bg\\tu_varna\\sit\\b1\\f22621631\\files\\users.xml");
    private User user;
    private UserList userList = UserList.getInstance();

    @Override
    public void execute() throws ParserConfigurationException, IOException, SAXException {
        read();
    }

    @Override
    public void read() throws IOException, ParserConfigurationException, SAXException {
        if (!FILE_NAME.exists()) {
            FILE_NAME.createNewFile();
        }

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        if (FILE_NAME.length() <= 20) {
            return;
        }
        Document document = documentBuilder.parse(FILE_NAME);

        document.getDocumentElement().normalize();
        NodeList nodeList = document.getElementsByTagName("user");

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element)node;
                user = new User(
                        element.getElementsByTagName("username").item(0).getTextContent(),
                        element.getElementsByTagName("password").item(0).getTextContent(),
                        PermissionLevel.valueOf(element.getElementsByTagName("permissionLevel").item(0).getTextContent())
                );
                userList.add(user);
            }
        }
    }
}
