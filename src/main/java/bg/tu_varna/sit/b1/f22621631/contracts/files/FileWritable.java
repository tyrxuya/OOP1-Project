package main.java.bg.tu_varna.sit.b1.f22621631.contracts.files;

import org.w3c.dom.Document;

import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.io.OutputStream;

public interface FileWritable {
    void write(Document document, OutputStream outputStream) throws TransformerException;
    void writeXml(Document document) throws IOException, TransformerException;
}
