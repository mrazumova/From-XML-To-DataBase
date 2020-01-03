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

        /*if(parser.parseToCSV("ADDROB", inPath, outPath)) {
            try {
                MySQLConnection database = new MySQLConnection();
                database.readToDB("ADDROB", outPath);
            } catch (Exception e){
                e.printStackTrace();
            }
        }*/

              if(parser.parseToCSV("CENTERST", inPath, outPath)) {
            try {
               MySQLConnection database = new MySQLConnection();
               database.readToDB("CENTERST", outPath);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
               if(parser.parseToCSV("CURENTST", inPath, outPath)) {
            try {
               MySQLConnection database = new MySQLConnection();
               database.readToDB("CURENTST", outPath);
            } catch (Exception e){
                e.printStackTrace();
            }
        }

           /*   if(parser.parseToCSV("DADDROB", inPath, outPath)) {
            try {
               MySQLConnection database = new MySQLConnection();
               database.readToDB("DADDROB", outPath);
            } catch (Exception e){
                e.printStackTrace();
            }
        }*/
        if(parser.parseToCSV("ESTSTAT", inPath, outPath)) {
            try {
                MySQLConnection database = new MySQLConnection();
                database.readToDB("ESTSTAT", outPath);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        if(parser.parseToCSV("FLATTYPE", inPath, outPath)) {
            try {
                MySQLConnection database = new MySQLConnection();
                database.readToDB("FLATTYPE", outPath);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        if(parser.parseToCSV("HSTSTAT", inPath, outPath)) {
            try {
                MySQLConnection database = new MySQLConnection();
                database.readToDB("HSTSTAT", outPath);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        if(parser.parseToCSV("INTVSTAT", inPath, outPath)) {
            try {
                MySQLConnection database = new MySQLConnection();
                database.readToDB("INTVSTAT", outPath);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        if(parser.parseToCSV("NDOCTYPE", inPath, outPath)) {
            try {
                MySQLConnection database = new MySQLConnection();
                database.readToDB("NDOCTYPE", outPath);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
