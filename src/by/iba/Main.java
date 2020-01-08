package by.iba;

import by.iba.connection.MySQLConnection;
import by.iba.parser.XMLParser;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        PropertyConfigurator.configure("log4j.properties");
        BasicConfigurator.configure();
        Logger logger = Logger.getLogger(Main.class);
        try{

            XMLParser parser = new XMLParser();
            MySQLConnection database = new MySQLConnection();
            logger.info("Connected to database.");
            String[] files  = AppProperties.INSTANCE.getFiles();

            for (String file : files){
                try {
                    ArrayList<String> columns = database.getColumns(file);
                    long time = System.currentTimeMillis();
                    parser.parse(columns, file, AppProperties.INSTANCE.getXMLPath(), AppProperties.INSTANCE.getCSVPath());
                    logger.info("File " + file + " parsed in " + (System.currentTimeMillis() - time));
                    time = System.currentTimeMillis();
                    database.readToDB(file, AppProperties.INSTANCE.getCSVPath());
                    logger.info("File " + file + " wrote in " + (System.currentTimeMillis() - time));
                }catch (Exception e){
                    e.printStackTrace();
                    logger.error(e.getMessage());
                }
            }
        }
        catch (Exception e){
            logger.error(e.getMessage());
        }

    }

}
