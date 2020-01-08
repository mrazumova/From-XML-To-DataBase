package by.iba.parser;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class XMLParser {

    public void parse(ArrayList<String> columns, String dataName, String inPath, String outPath) throws Exception{
        XMLStreamReader reader = XMLInputFactory.newInstance().createXMLStreamReader(Files.newInputStream(Paths.get(inPath + dataName + ".xml")));
        FileWriter writer = new FileWriter(outPath + dataName + ".csv");
        reader.next();
        while (reader.hasNext()) {
            int event = reader.next();
            if (event ==  XMLStreamConstants.START_ELEMENT) {
                int num = reader.getAttributeCount();
                for(int j = 0; j < columns.size(); ++j){
                    boolean f = true;
                    for (int i = 0; i < num && f; ++i){
                        if (columns.get(j).equals(reader.getAttributeName(i).toString())){
                            writer.append(reader.getAttributeValue(i));
                            f = false;
                        }
                    }
                    writer.append(';');
                }
                writer.append('\n');
            }
        }
        writer.flush();
        writer.close();
    }
}

