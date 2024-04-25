package main.java.bg.tu_varna.sit.b1.f22621631.contracts.controllers;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface RunnableCommand {
    void execute() throws IOException, ParserConfigurationException, TransformerException, SAXException;
}
