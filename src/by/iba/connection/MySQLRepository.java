package by.iba.connection;

import by.iba.exception.EmptyTableException;
import by.iba.properties.AppProperties;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MySQLRepository implements Repository {

    private Connection connection;

    private Statement statement;

    public static final Logger logger = Logger.getLogger(MySQLRepository.class);

    protected MySQLRepository() throws Exception {
        Class.forName(AppProperties.getDriver());
        connection = DriverManager.getConnection(AppProperties.getURL(), AppProperties.getUser(), AppProperties.getPassword());
        statement = connection.createStatement();
        logger.info("Connected to MySQL database.");
    }

    @Override
    public ArrayList<String> getColumns(String table) throws SQLException, EmptyTableException, FileNotFoundException {
        ArrayList<String> columns = new ArrayList<>();
        String sql = getSQL("getcolsmysql.sql").replace("table", table);
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet set = stmt.executeQuery();
        while (set.next()) {
            columns.add(set.getString("Field"));
        }
        if (columns.isEmpty())
            throw new EmptyTableException("There are no columns in table.");
        return columns;
    }

    @Override
    public int loadFile(String table) throws SQLException, FileNotFoundException {
        long time = System.currentTimeMillis();
        String sql = getSQL("loadmysql.sql").replace("table", table)
                .replace("path", AppProperties.getCSVPath())
                .replace("separator", AppProperties.getSeparator());

        int rows = statement.executeUpdate(sql);

        logger.info("File " + table + " loaded into database in " + (System.currentTimeMillis() - time) + ", number of records: " + rows);
        return rows;
    }

    @Override
    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
    }

    private String getSQL(String s) throws FileNotFoundException {
        try{
            Scanner sc = new Scanner(new File("config/" + s));
            return sc.nextLine();
        }catch (Exception e){
            throw new FileNotFoundException("SQL source file not found.");
        }
    }
}
