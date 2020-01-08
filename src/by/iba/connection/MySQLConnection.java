package by.iba.connection;

import by.iba.AppProperties;

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

    public ArrayList<String> getColumns(String table){
        ArrayList<String> columns = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement("SHOW COLUMNS FROM " + table +";");
            ResultSet set = stmt.executeQuery();
            while (set.next()){
                columns.add(set.getString("Field"));
            }
        } catch(SQLException e){ e.printStackTrace(); }
        return columns;
    }

    public void readToDB(String table, String path){
        try {
            String sql = "LOAD DATA LOCAL INFILE '" + path
                    + table + ".csv'" + " INTO TABLE " + table +
                    " COLUMNS TERMINATED BY ';'" +
                    " LINES TERMINATED BY '\\n';";

            statement.executeUpdate(sql);
        } catch(SQLException e){
            System.out.println("Error while writing data.");
            e.printStackTrace();
        }
    }

}
