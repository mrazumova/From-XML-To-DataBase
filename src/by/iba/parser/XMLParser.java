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

    private File stylesheet;
    private File inFile;
    private File outFile;

    public XMLParser(String dataName, String inPath, String outPath) {
        setStylesheet("src/" + dataName + ".xsl");
        setInFile(inPath + dataName + ".xml");
        setOutFile(outPath + dataName + ".csv");
    }

    public boolean parseToCSV(){
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(inFile);

            StreamSource stylesource = new StreamSource(stylesheet);
            Transformer transformer = TransformerFactory.newInstance().newTransformer(stylesource);
            Source source = new DOMSource(document);
            Result outputTarget = new StreamResult(outFile);
            transformer.transform(source, outputTarget);
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    public void setInFile(String inFile) {
        this.inFile = new File(inFile);
    }

    public void setOutFile(String outFile) {
        this.outFile = new File(outFile);
    }

    public void setStylesheet(String stylesheet) {
        this.stylesheet = new File(stylesheet);
    }
}