package main.java.bg.tu_varna.sit.b1.f22621631.contracts.files;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public interface FileReadable {
    void read() throws IOException, ParserConfigurationException, SAXException;
}
