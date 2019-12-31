package by.iba.connection;

import by.iba.parser.XMLParser;

import javax.swing.table.TableCellEditor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLConnection {

    public static final String DB_URL = "jdbc:mysql://localhost/FIAS";
    public static final String USER = "root";
    public static final String PASS = "root";

    private Connection connection;
    private Statement statement;

    public MySQLConnection() throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(DB_URL, USER, PASS);
        statement = connection.createStatement();
    }

    public void readToDB(String table, String path){
        try {
            String sql = "LOAD DATA INFILE '" + path + table + ".csv'" + " INTO TABLE " + table +
                    " COLUMNS TERMINATED BY ','" +
                    " LINES TERMINATED BY '\\r\n';";

            System.out.println(statement.executeUpdate(sql));
        } catch(SQLException e){
            System.out.println("Error while writing data.");
            e.printStackTrace();
        }
    }

}
