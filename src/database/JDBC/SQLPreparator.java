package database.JDBC;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class SQLPreparator {
    public abstract void prepareSQL(PreparedStatement preparedStatement) throws SQLException;
}
