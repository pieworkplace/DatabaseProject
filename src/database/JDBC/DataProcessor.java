package database.JDBC;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DataProcessor{
    public abstract void processData(ResultSet resultSet) throws SQLException;
}
