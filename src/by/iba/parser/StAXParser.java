package by.iba.parser;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class StAXParser {

    public void parse(String dataName, String inPath, String outPath) throws Exception{
        XMLStreamReader reader = XMLInputFactory.newInstance().createXMLStreamReader(Files.newInputStream(Paths.get(inPath + dataName + ".xml")));
        FileWriter writer = new FileWriter(outPath + dataName + ".csv");
        reader.next();
        while (reader.hasNext()) {
            int event = reader.next();
            if (event ==  XMLStreamConstants.START_ELEMENT) {
                int num = reader.getAttributeCount() - 1;
                for (int i = 0; i < num; ++i){
                    writer.append(reader.getAttributeValue(i));
                    writer.append(';');
                }
                writer.append(reader.getAttributeValue(num));
                writer.append('\n');
            }
        }
        writer.flush();
        writer.close();
    }
}

