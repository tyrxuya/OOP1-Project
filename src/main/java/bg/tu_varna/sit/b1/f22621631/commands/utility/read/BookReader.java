package main.java.bg.tu_varna.sit.b1.f22621631.commands.utility.read;

import main.java.bg.tu_varna.sit.b1.f22621631.contracts.controllers.RunnableCommand;
import main.java.bg.tu_varna.sit.b1.f22621631.contracts.files.FileReadable;
import main.java.bg.tu_varna.sit.b1.f22621631.lists.BookList;
import main.java.bg.tu_varna.sit.b1.f22621631.models.authors.Author;
import main.java.bg.tu_varna.sit.b1.f22621631.models.books.Book;
import main.java.bg.tu_varna.sit.b1.f22621631.models.books.enums.Genre;
import main.java.bg.tu_varna.sit.b1.f22621631.models.books.enums.Rating;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;

public class BookReader implements FileReadable, RunnableCommand {
    private final File STANDARD_FILE = new File(".\\src\\main\\java\\bg\\tu_varna\\sit\\b1\\f22621631\\files\\books.xml");

    @Override
    public void execute() throws ParserConfigurationException {
        read();
    }

    @Override
    public void read() {
        try {
            if (!STANDARD_FILE.exists()) {
                STANDARD_FILE.createNewFile();
            }
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            if (STANDARD_FILE.length() <= 20) {
                return;
            }
            Document document = documentBuilder.parse(STANDARD_FILE);

            document.getDocumentElement().normalize();
            NodeList nodeList = document.getElementsByTagName("book");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    Book book = new Book.Builder(
                            new Author(
                                    element.getElementsByTagName("firstName").item(0).getTextContent(),
                                    element.getElementsByTagName("lastName").item(0).getTextContent(),
                                    element.getElementsByTagName("country").item(0).getTextContent()
                            ),
                            element.getElementsByTagName("title").item(0).getTextContent(),
                            Integer.parseInt(element.getElementsByTagName("publishingYear").item(0).getTextContent()),
                            element.getElementsByTagName("isbn").item(0).getTextContent()
                    )
                            .description(element.getElementsByTagName("description").getLength() != 0 ?
                                    element.getElementsByTagName("description").item(0).getTextContent() : null)
                            .genre(element.getElementsByTagName("genre").getLength() != 0 ?
                                    Genre.valueOf(element.getElementsByTagName("genre").item(0).getTextContent()) : null)
                            .keyWords(element.getElementsByTagName("keyWords").getLength() != 0 ?
                                    element.getElementsByTagName("keyWords").item(0).getTextContent() : null)
                            .rating(element.getElementsByTagName("rating").getLength() != 0 ?
                                    Rating.valueOf(element.getElementsByTagName("rating").item(0).getTextContent()) : null)
                            .build();

                    BookList.getInstance().add(book);
                }
            }

//            System.out.println("Successfully opened books.xml!");
        } catch (Exception ex) {
            System.out.println("Error in reading books.xml!");
        }
    }
}
