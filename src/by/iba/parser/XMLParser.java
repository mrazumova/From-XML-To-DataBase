package by.iba.parser;

import by.iba.properties.AppProperties;
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
    public static final  Logger logger = Logger.getLogger(XMLParser.class);

    public int parse(ArrayList<String> columns, String dataName) throws Exception{
        long time = System.currentTimeMillis();
        int records = 0;
        XMLStreamReader reader = XMLInputFactory.newInstance().createXMLStreamReader(Files.newInputStream(Paths.get(AppProperties.getXMLPath() + dataName + ".xml")));
        FileWriter writer = new FileWriter(AppProperties.getCSVPath() + dataName + ".csv");
        reader.next();
        while (reader.hasNext()) {
            int event = reader.next();
            if (event ==  XMLStreamConstants.START_ELEMENT) {
                readRow(reader, writer, columns);
                ++records;
            }
        }
        writer.flush();
        writer.close();

        logger.info("File " + dataName + " parsed in " + (System.currentTimeMillis() - time) + ", number of records: " + records);
        return records;
    }

    private void readRow(XMLStreamReader reader, FileWriter writer, ArrayList<String> columns) throws IOException {
        int num = reader.getAttributeCount();
        for(int j = 0; j < columns.size(); ++j){
            for (int i = 0; i < num; ++i){
                if (columns.get(j).equals(reader.getAttributeName(i).toString())){
                    writer.append(reader.getAttributeValue(i).replace("\r\n"," "));
                    break;
                }
            }
            if (j != columns.size() - 1)
                writer.append(AppProperties.getSeparator());
        }
        writer.append("\r\n");
    }
}

