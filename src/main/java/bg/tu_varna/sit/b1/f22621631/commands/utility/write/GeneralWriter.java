package main.java.bg.tu_varna.sit.b1.f22621631.commands.utility.write;

import main.java.bg.tu_varna.sit.b1.f22621631.contracts.controllers.RunnableCommand;
import main.java.bg.tu_varna.sit.b1.f22621631.contracts.files.FileWritable;
import org.w3c.dom.Document;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.OutputStream;

public abstract class GeneralWriter implements FileWritable, RunnableCommand {
    private final String DEFAULT_PATH = ".\\src\\main\\java\\bg\\tu_varna\\sit\\b1\\f22621631\\files\\";

    public String getDEFAULT_PATH() {
        return DEFAULT_PATH;
    }

    @Override
    public void write(Document document, OutputStream outputStream) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

        DOMSource domSource = new DOMSource(document);
        StreamResult streamResult = new StreamResult(outputStream);

        transformer.transform(domSource, streamResult);
    }


}
