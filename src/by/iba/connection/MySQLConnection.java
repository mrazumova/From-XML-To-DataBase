package by.iba.connection;

import by.iba.properties.AppProperties;

import java.sql.*;
import java.util.ArrayList;

public class MySQLConnection {
    private Connection connection;
    private Statement statement;

    public MySQLConnection() throws Exception{
        Class.forName(AppProperties.INSTANCE.getDriver());
        connection = DriverManager.getConnection(AppProperties.INSTANCE.getURL(), AppProperties.INSTANCE.getUser(), AppProperties.INSTANCE.getPassword());
        statement = connection.createStatement();
    }

    public ArrayList<String> getColumns(String table) throws SQLException{
        ArrayList<String> columns = new ArrayList<>();
        PreparedStatement stmt = connection.prepareStatement("SHOW COLUMNS FROM " + table +";");
        ResultSet set = stmt.executeQuery();
        while (set.next()){
            columns.add(set.getString("Field"));
        }
        return columns;
    }

    public void readToDB(String table, String path) throws SQLException{
        String sql = "LOAD DATA LOCAL INFILE '" + path
                + table + ".csv'" + " INTO TABLE " + table +
                " COLUMNS TERMINATED BY ';'" +
                " LINES TERMINATED BY '\\n';";

        statement.executeUpdate(sql);
    }

}
