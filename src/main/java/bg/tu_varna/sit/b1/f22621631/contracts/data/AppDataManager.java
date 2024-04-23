package main.java.bg.tu_varna.sit.b1.f22621631.contracts.data;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileNotFoundException;

public interface AppDataManager {
    void load() throws Exception;
    void unload() throws Exception;
}
