package by.iba.connection;

import java.io.File;
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

    public void readToDB(File inputFile){
        createTable();

        try {
            String sql = "LOAD DATA LOCAL INFILE 'src/ACTSTAT.csv' INTO TABLE ACTSTAT" +
                    "    FIELDS TERMINATED BY ','" +
                    "    ENCLOSED BY '\"'" +
                    "    LINES TERMINATED BY '\\r\\n'" +
                    "    IGNORE 1 LINES" +
                    "    (ACTSTATID, NAME);";

            statement.executeUpdate(sql);
        } catch(SQLException e){
            System.out.println("Error while writing data.");
            e.printStackTrace();
        }
    }

    private void createTable() {
            try{
            String sql = "CREATE TABLE IF NOT EXISTS ACTSTAT" +
                    "(" +
                    "    `ACTSTATID` decimal(24,5) DEFAULT NULL," +
                    "    `NAME` varchar(100) DEFAULT NULL" +
                    ")DEFAULT CHARSET=utf8;";

            statement.executeUpdate(sql);
        } catch (SQLException e){
            System.out.println("Error while creating a table.");
        }
    }
}
