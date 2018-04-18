package database.JDBC;

import java.sql.*;

public final class DBConnectionUtil {

    private static String url = "jdbc:mysql://academic-mysql.cc.gatech.edu:3306/cs4400_team_62";
    private static String user = "cs4400_team_62";
    private static String password = "QLk8PpuY";

    private DBConnectionUtil(){}

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static Connection getConnection(){
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    private static Statement getStatement(Connection connection){
        Statement statement = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    private static ResultSet getResultSet(String sql, Statement statement){
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static ResultSet getResultSet(PreparedStatement statement){
        try {
            ResultSet resultSet = statement.executeQuery();
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void update(String sql){
        Connection connection = DBConnectionUtil.getConnection();
        Statement statement = DBConnectionUtil.getStatement(connection);
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBConnectionUtil.release(statement, connection);
    }

    public static void select(String sql, DataProcessor dataProcessor){
        Connection connection = DBConnectionUtil.getConnection();
        Statement statement = DBConnectionUtil.getStatement(connection);
        ResultSet resultSet = DBConnectionUtil.getResultSet(sql, statement);
        try {
            dataProcessor.processData(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBConnectionUtil.release(resultSet, statement, connection);
    }

    public static void preSelect(String sql, SQLPreparator sqlPreparator ,DataProcessor dataProcessor){
        Connection connection = DBConnectionUtil.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            sqlPreparator.prepareSQL(preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResultSet resultSet = DBConnectionUtil.getResultSet(preparedStatement);
        try {
            dataProcessor.processData(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBConnectionUtil.release(resultSet, preparedStatement, connection);
    }

    private static void release(ResultSet resultSet, Statement statement, Connection connection){
        if (resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                release(statement, connection);
            }
        }
    }

    private static void release(Statement statement, Connection connection){
        if (statement != null){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (connection != null){
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
