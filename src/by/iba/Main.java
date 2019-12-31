package by.iba;

import by.iba.connection.MySQLConnection;
import by.iba.parser.XMLParser;

public class Main {

    public static void main(String[] args) {
        XMLParser parser = new XMLParser("src/ACTSTAT.xsl", "src/ACTSTAT.xml", "C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/ACTSTAT.csv");
        if(parser.parseToCSV()) {
            try {
                MySQLConnection database = new MySQLConnection();
                database.readToDB();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
