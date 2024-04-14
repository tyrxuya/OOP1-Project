package main.java.bg.tu_varna.sit.b1.f22621631.commands.read;

import main.java.bg.tu_varna.sit.b1.f22621631.contracts.FileReadable;
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
import java.io.File;

public class ReadBooks implements FileReadable {
    private final File FILE_NAME = new File(".\\src\\main\\java\\bg\\tu_varna\\sit\\b1\\f22621631\\files\\books.xml");
    private Book book;
    private BookList bookList = BookList.getInstance();

    @Override
    public void read() {
        try {
            if (!FILE_NAME.exists()) {
                FILE_NAME.createNewFile();
            }
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(FILE_NAME);

            document.getDocumentElement().normalize();
            NodeList nodeList = document.getElementsByTagName("book");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    book = new Book.Builder(
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

                    bookList.add(book);
                }
            }

//            System.out.println("Successfully opened books.xml!");
        } catch (Exception ex) {
            System.out.println("Error in reading books.xml!");
        }
    }
}
