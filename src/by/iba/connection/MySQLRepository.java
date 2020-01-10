package by.iba.connection;

import by.iba.exception.EmptyTableException;
import by.iba.properties.AppProperties;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;

public class MySQLRepository implements Repository {
    private Connection connection;
    private Statement statement;

    private Logger logger = Logger.getLogger(MySQLRepository.class);

    protected MySQLRepository() throws Exception{
        Class.forName(AppProperties.getDriver());
        connection = DriverManager.getConnection(AppProperties.getURL(), AppProperties.getUser(), AppProperties.getPassword());
        statement = connection.createStatement();
        logger.info("Connected to database.");
    }

    @Override
    public ArrayList<String> getColumns(String table) throws SQLException, EmptyTableException{
        ArrayList<String> columns = new ArrayList<>();
        PreparedStatement stmt = connection.prepareStatement("SHOW COLUMNS FROM " + table +";");
        ResultSet set = stmt.executeQuery();
        while (set.next()){
            columns.add(set.getString("Field"));
        }
        if(columns.isEmpty())
            throw new EmptyTableException("There are no columns in table.");
        return columns;
    }

    @Override
    public void loadFile(String table, String path) throws SQLException{
        long time = System.currentTimeMillis();

        String sql = "LOAD DATA LOCAL INFILE '" + path
                + table + ".csv'" + " INTO TABLE " + table +
                " COLUMNS TERMINATED BY ';'" +
                " LINES TERMINATED BY '\\n';";

        statement.executeUpdate(sql);

        logger.info("File " + table + " loaded into database in " + (System.currentTimeMillis() - time));
    }
}
