package by.iba.properties;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public enum AppProperties {
    INSTANCE;
    private final Properties properties;

    AppProperties() {
        properties = new Properties();
        try {
            properties.load(new FileInputStream("src/app.properties"));
        } catch (IOException e) {
            Logger.getLogger(AppProperties.class.getName()).error(e.toString());
        }
    }

    public String getDriver() {
        return properties.getProperty("db_driver");
    }

    public String getURL(){
        return properties.getProperty("db_url");
    }

    public String getUser(){
        return properties.getProperty("user");
    }

    public String getPassword(){
        return properties.getProperty("password");
    }

    public String getXMLPath(){
        return properties.getProperty("xml_path");
    }

    public String getCSVPath(){
        return properties.getProperty("csv_path");
    }

    public String[] getFiles(){
        return properties.getProperty("files").split(";");
    }
}
