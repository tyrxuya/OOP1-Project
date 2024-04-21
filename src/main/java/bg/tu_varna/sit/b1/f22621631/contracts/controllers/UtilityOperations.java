package main.java.bg.tu_varna.sit.b1.f22621631.contracts.controllers;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;

public interface UtilityOperations {
    File open(String fileName) throws FileNotFoundException;
    void save() throws FileNotFoundException, ParserConfigurationException;
    void saveAs(String newFileName) throws FileNotFoundException, ParserConfigurationException;
    void help();
    void exit();
}
