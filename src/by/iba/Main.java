package by.iba;

import by.iba.connection.MySQLConnection;
import by.iba.parser.XMLParser;

public class Main {

    public static String outPath = "C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/";
    public static String inPath = "C:/Users/Razumava_M/IdeaProjects/Data/";

    public static void main(String[] args) {
        XMLParser parser = new XMLParser("ACTSTAT", inPath, outPath);
        if(parser.parseToCSV()) {
            try {
                MySQLConnection database = new MySQLConnection();
                database.readToDB("ACTSTAT", outPath);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
