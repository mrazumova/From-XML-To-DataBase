package by.iba.connection;

import by.iba.exception.EmptyTableException;
import by.iba.properties.AppProperties;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;

public class MSServerRepository implements Repository {

    private Connection connection;

    private Statement statement;

    private Logger logger = Logger.getLogger(MySQLRepository.class);

    protected MSServerRepository() throws Exception{
        Class.forName(AppProperties.getDriver());
        connection = DriverManager.getConnection(AppProperties.getURL(), AppProperties.getUser(), AppProperties.getPassword());
        statement = connection.createStatement();
        logger.info("Connected to database.");
    }
    @Override
    public ArrayList<String> getColumns(String table) throws SQLException, EmptyTableException {
        ArrayList<String> columns = new ArrayList<>();
        PreparedStatement stmt = connection.prepareStatement("SELECT COLUMN_NAME\n" +
                " FROM INFORMATION_SCHEMA.COLUMNS\n" +
                " WHERE TABLE_NAME LIKE '" + table + "';");
        ResultSet set = stmt.executeQuery();
        while (set.next()){
            columns.add(set.getString(1));
        }
        if(columns.isEmpty())
            throw new EmptyTableException("There are no columns in table.");
        return columns;
    }

    @Override
    public void loadFile(String table, String path, String separator) throws SQLException {
        long time = System.currentTimeMillis();

        String sql = "BULK INSERT " + table +
                " FROM '" + path + table + ".csv'" +
                " WITH (FIELDTERMINATOR='" + separator + "'," +
                "    ROWTERMINATOR='\n'," +
                "    MAXERRORS=9999999," +
                "    CODEPAGE='utf8'," +
                "    ERRORFILE = '" + path + table + "errors.txt');";

        statement.executeUpdate(sql);

        logger.info("File " + table + " loaded into database in " + (System.currentTimeMillis() - time));
    }

    @Override
    public void close(){
        if (connection != null) {
            try {
                connection.close();
                logger.info("Connection is closed.");
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
    }
}
