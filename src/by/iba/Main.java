package by.iba;

import by.iba.connection.MySQLConnection;
import by.iba.parser.XMLParser;

public class Main {

    public static String outPath = "C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/";
    public static String inPath = "C:/Users/Razumava_M/IdeaProjects/Data/";

    public static void main(String[] args) {
        XMLParser parser = new XMLParser();
        if(parser.parseToCSV("ACTSTAT", inPath, outPath)) {
            try {
                MySQLConnection database = new MySQLConnection();
                database.readToDB("ACTSTAT", outPath);
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        if(parser.parseToCSV("DEL_ADDROBJ", inPath, outPath)) {
            try {
               MySQLConnection database = new MySQLConnection();
               database.readToDB("DEL_ADDROBJ", outPath);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
