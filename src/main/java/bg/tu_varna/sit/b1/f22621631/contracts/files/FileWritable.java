package main.java.bg.tu_varna.sit.b1.f22621631.contracts.files;

import main.java.bg.tu_varna.sit.b1.f22621631.models.books.Book;
import org.w3c.dom.Document;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public interface FileWritable {
    void write(Document document, OutputStream outputStream) throws TransformerException;
    void writeXml(Document document) throws IOException, TransformerException;
}
