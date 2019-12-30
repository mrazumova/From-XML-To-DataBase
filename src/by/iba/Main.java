package by.iba;

import by.iba.connection.MySQLConnection;
import by.iba.parser.XMLParser;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        XMLParser parser = new XMLParser("src/ACTSTAT.xsl", "src/ACTSTAT.xml", "src/ACTSTAT.csv");
        if(parser.parseToCSV()) {
            try {
                MySQLConnection database = new MySQLConnection();
                database.readToDB(new File("src/ACTSTAT.csv"));
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
