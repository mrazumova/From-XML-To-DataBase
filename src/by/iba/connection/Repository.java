package by.iba.connection;

import by.iba.exception.EmptyTableException;

import java.sql.SQLException;
import java.util.ArrayList;

public interface Repository {

    ArrayList<String> getColumns(String table) throws SQLException, EmptyTableException;

    void loadFile(String table, String path) throws SQLException;
}
