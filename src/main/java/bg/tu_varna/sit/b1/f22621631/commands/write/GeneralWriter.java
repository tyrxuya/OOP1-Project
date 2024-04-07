package main.java.bg.tu_varna.sit.b1.f22621631.commands.write;

import main.java.bg.tu_varna.sit.b1.f22621631.contracts.FileWritable;
import org.w3c.dom.Document;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.OutputStream;

public abstract class GeneralWriter implements FileWritable {
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
