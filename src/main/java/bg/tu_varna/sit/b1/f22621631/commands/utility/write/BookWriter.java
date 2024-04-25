package main.java.bg.tu_varna.sit.b1.f22621631.commands.utility.write;

import main.java.bg.tu_varna.sit.b1.f22621631.contracts.files.FileWritable;
import main.java.bg.tu_varna.sit.b1.f22621631.lists.BookList;
import main.java.bg.tu_varna.sit.b1.f22621631.models.books.Book;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class BookWriter extends GeneralWriter implements FileWritable {
    private File file = new File(getFILE_PATH().concat("books.xml"));

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    @Override
    public void execute() throws ParserConfigurationException, IOException, TransformerException {
        writeFile(BookList.getInstance().getBookList());
        setFile(new File(getFILE_PATH().concat("books.xml")));
    }

    public void writeFile(List<Book> books) throws ParserConfigurationException, IOException, TransformerException {
        writeXml(generateDocument(books));
    }

    @Override
    public void writeXml(Document document) throws IOException, TransformerException {
        if (!file.exists()) {
            file.createNewFile();
        }
        write(document, new FileOutputStream(file));
    }

    private Document generateDocument(List<Book> books) throws ParserConfigurationException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

        Document document = documentBuilder.newDocument();
        Element root = document.createElement("list");
//        if (books.isEmpty()) {
//            document.appendChild(root.appendChild(document.createTextNode("")));
//            return document;
//        }
        document.appendChild(root);



        for (Book currentBook : books) {
            Element book = document.createElement("book");

            Element author = document.createElement("author");

            Element authorFirstName = document.createElement("firstName");
            Text authorFistNameText = document.createTextNode(currentBook.getAuthor().getFirstName());
            authorFirstName.appendChild(authorFistNameText);

            Element authorLastName = document.createElement("lastName");
            Text authorLastNameText = document.createTextNode(currentBook.getAuthor().getLastName());
            authorLastName.appendChild(authorLastNameText);

            Element authorCountry = document.createElement("country");
            Text authorCountryText = document.createTextNode(currentBook.getAuthor().getCountry());
            authorCountry.appendChild(authorCountryText);

            author.appendChild(authorFirstName);
            author.appendChild(authorLastName);
            author.appendChild(authorCountry);

            Element title = document.createElement("title");
            Text titleText = document.createTextNode(currentBook.getTitle());
            title.appendChild(titleText);

            Element publishingYear = document.createElement("publishingYear");
            Text publishingYearText = document.createTextNode(String.valueOf(currentBook.getPublishingYear()));
            publishingYear.appendChild(publishingYearText);

            Element isbn = document.createElement("isbn");
            Text isbnText = document.createTextNode(currentBook.getIsbn());
            isbn.appendChild(isbnText);

            book.appendChild(author);
            book.appendChild(title);
            book.appendChild(publishingYear);
            book.appendChild(isbn);

            if (currentBook.getGenre() != null) {
                Element genre = document.createElement("genre");
                Text genreText = document.createTextNode(currentBook.getGenre().toString());
                genre.appendChild(genreText);
                book.appendChild(genre);
            }

            if (currentBook.getDescription() != null) {
                Element description = document.createElement("description");
                Text descriptionText = document.createTextNode(currentBook.getDescription());
                description.appendChild(descriptionText);
                book.appendChild(description);
            }

            if (currentBook.getKeyWords() != null) {
                Element keyWords = document.createElement("keyWords");
                Text keyWordsText = document.createTextNode(currentBook.getKeyWords());
                keyWords.appendChild(keyWordsText);
                book.appendChild(keyWords);
            }

            if (currentBook.getRating() != null) {
                Element rating = document.createElement("rating");
                Text ratingText = document.createTextNode(currentBook.getRating().toString());
                rating.appendChild(ratingText);
                book.appendChild(rating);
            }

            root.appendChild(book);
        }

        return document;
    }
}
