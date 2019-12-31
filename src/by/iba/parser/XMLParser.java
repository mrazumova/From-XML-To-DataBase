package by.iba.parser;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.IOException;

public class XMLParser {

    public boolean parseToCSV(String dataName, String inPath, String outPath){
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File((inPath + dataName + ".xml")));

            StreamSource stylesource = new StreamSource(new File("src/" + dataName + ".xsl"));
            Transformer transformer = TransformerFactory.newInstance().newTransformer(stylesource);
            Source source = new DOMSource(document);
            Result outputTarget = new StreamResult(new File(outPath + dataName + ".csv"));
            transformer.transform(source, outputTarget);
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

}