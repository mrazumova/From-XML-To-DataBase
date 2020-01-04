package by.iba;

import by.iba.connection.MySQLConnection;
import by.iba.parser.StAXParser;

public class Main {

    public static String outPath = "C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/";
    public static String inPath = "C:/Users/Razumava_M/IdeaProjects/Data/";

    public static String[] files  = {"ACTSTAT","ADDROB","CENTERST","CURENTST",
            "DADDROB","ESTSTAT","FLATTYPE","HOUSE","HSTSTAT","INTVSTAT","NDOCTYPE",
            "NORDOC", "OPERSTAT", "ROOM", "SOCRBASE", "STEAD", "STRSTAT"
    };

    public static void main(String[] args) {
        StAXParser parser = new StAXParser();

        for (String file : files){
            try {
                long time = System.currentTimeMillis();
                parser.parse(file, inPath, outPath);
                System.out.print("File " + file + " parsed in " + (System.currentTimeMillis() - time));
                MySQLConnection database = new MySQLConnection();
                time = System.currentTimeMillis();
                database.readToDB(file, outPath);
                System.out.println(", wrote in " + (System.currentTimeMillis() - time));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
