package by.iba.parser;

import org.apache.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class XMLParser{
    private Logger logger = Logger.getLogger(XMLParser.class);

    public void parse(ArrayList<String> columns, String dataName, String inPath, String outPath) throws Exception{
        long time = System.currentTimeMillis();

        XMLStreamReader reader = XMLInputFactory.newInstance().createXMLStreamReader(Files.newInputStream(Paths.get(inPath + dataName + ".xml")));
        FileWriter writer = new FileWriter(outPath + dataName + ".csv");
        reader.next();
        while (reader.hasNext()) {
            int event = reader.next();
            if (event ==  XMLStreamConstants.START_ELEMENT) {
                readRow(reader, writer, columns);
                writer.append('\n');
            }
        }
        writer.flush();
        writer.close();

        logger.info("File " + dataName + " parsed in " + (System.currentTimeMillis() - time));
    }

    private void readRow(XMLStreamReader reader, FileWriter writer, ArrayList<String> columns) throws IOException {
        int num = reader.getAttributeCount();
        for(int j = 0; j < columns.size(); ++j){
            for (int i = 0; i < num; ++i){
                if (columns.get(j).equals(reader.getAttributeName(i).toString())){
                    writer.append(reader.getAttributeValue(i));
                    break;
                }
            }
            writer.append(';');
        }
    }
}

