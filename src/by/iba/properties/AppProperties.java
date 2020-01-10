package by.iba.properties;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class AppProperties {

    private static Properties properties = new Properties();

    static {
        try {
            properties.load(new FileInputStream("src/newapp.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getDatabaseType(){
        return properties.getProperty("db_type");
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
}
