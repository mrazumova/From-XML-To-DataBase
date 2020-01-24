package by.iba.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class AppProperties {

    private static Properties properties = new Properties();

    static{

        try {
            properties.load(new FileInputStream("config/app.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getDatabaseType(){
        return properties.getProperty("db_type").toLowerCase();
    }

    public static String getDriver() {
        return properties.getProperty("db_driver");
    }

    public static String getURL(){
        return properties.getProperty("db_url");
    }

    public static String getUser(){
        return properties.getProperty("user");
    }

    public static String getPassword(){
        return properties.getProperty("password");
    }

    public static String getXMLPath(){
        return properties.getProperty("xml_path");
    }

    public static String getCSVPath(){
        return properties.getProperty("csv_path");
    }

    public static String[] getFiles(){
        return properties.getProperty("files").split(";");
    }

    public static boolean parseToCSV(){
        return Boolean.parseBoolean(properties.getProperty("parse_to_csv"));
    }

    public static boolean loadToDB(){
        return Boolean.parseBoolean(properties.getProperty("load_to_db"));
    }

    public static String getSeparator(){
        return properties.getProperty("separator");
    }
}
