package by.iba.parser;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.Iterator;

public class StAXParser {

    public boolean parseToCSV(String dataName, String inPath, String outPath){
        try{
            XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
            XMLEventReader reader = xmlInputFactory.createXMLEventReader(new FileInputStream(inPath + dataName + ".xml"));
            FileWriter writer = new FileWriter(outPath + dataName + ".csv");
            while (reader.hasNext()){
                XMLEvent event = reader.nextEvent();
                if(event.isStartElement()){
                    StartElement startElement = event.asStartElement();
                    Iterator<Attribute> attributeIterator = startElement.getAttributes();
                    while (attributeIterator.hasNext()){
                        Attribute attribute = attributeIterator.next();
                        writer.append(attribute.getValue());
                        if (attributeIterator.hasNext()){
                            writer.append(';');
                        }
                        else{
                            writer.append('\n');
                        }
                    }
                }
            }
            writer.flush();
            writer.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return true;
    }

}
