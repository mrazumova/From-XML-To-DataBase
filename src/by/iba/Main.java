package by.iba;

import by.iba.connection.Repository;
import by.iba.connection.RepositoryFactory;
import by.iba.parser.XMLParser;
import by.iba.properties.AppProperties;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        PropertyConfigurator.configure("config/log4j.properties");
        Repository repository = null;
        XMLParser parser;
        try {
            parser = new XMLParser();
            //get files names
            String[] files = AppProperties.getFiles();
            //get connection to database
            repository = RepositoryFactory.getRepository(AppProperties.getDatabaseType());
            for (String file : files) {
                try {
                    //parse to CSV
                    if (AppProperties.parseToCSV()) {
                        parser.parse(repository.getColumns(file), file);
                    }
                    //insert into database
                    if(AppProperties.loadToDB()){
                        repository.loadFile(file);
                    }
                } catch (Exception e) {
                    logger.error("An error occurred during processing.", e);
                }
            }
            logger.info("Done.");
        } catch (Exception e) {
            logger.error("Process failed", e);
        } finally {
            //close connection
            repository.close();
        }
    }
}
