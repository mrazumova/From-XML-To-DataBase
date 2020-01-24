package by.iba.connection;

import by.iba.exception.EmptyTableException;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface Repository {

    ArrayList<String> getColumns(String table) throws SQLException, EmptyTableException, FileNotFoundException;

    int loadFile(String table) throws SQLException, FileNotFoundException;

    void close();
}
