package by.iba;

import by.iba.connection.Repository;
import by.iba.connection.RepositoryFactory;
import by.iba.parser.XMLParser;
import by.iba.properties.AppProperties;
import org.apache.log4j.Logger;

public class Main {

    private static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        try{
            XMLParser parser = new XMLParser();
            //get files names
            String[] files  = AppProperties.getFiles();
            //get connection to database
            Repository repository = RepositoryFactory.getRepository(AppProperties.getDatabaseType());

            for (String file : files){
                try {
                    // parse to CSV
                    parser.parse(repository.getColumns(file), file, AppProperties.getXMLPath(), AppProperties.getCSVPath());
                    //insert into database
                    repository.loadFile(file, AppProperties.getCSVPath());
                }catch (Exception e){
                    e.printStackTrace();
                    logger.error(e.toString());
                }
            }
            logger.info("Done.");
        }
        catch (Exception e){
            e.printStackTrace();
            logger.error(e.toString());
        }
    }
}
