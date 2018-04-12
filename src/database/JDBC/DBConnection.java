package database.JDBC;

import java.sql.*;

public class DBConnection {
    private static final DBConnection DB_CONNECTION = new DBConnection();
    private static Connection CONNECTION;

    private DBConnection(){
        try {
            //1. load driver manager
            Class.forName("com.mysql.jdbc.Driver");
            //2. connect
            CONNECTION = DriverManager.getConnection("jdbc:mysql://academic-mysql.cc.gatech.edu:3306/cs4400_team_62", "cs4400_team_62", "QLk8PpuY");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static String test(){
        StringBuilder result = new StringBuilder();
        // execute SQL
        try {
            String sql = "select * from example";
            Statement statement = CONNECTION.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                result.append(name);
                result.append(" ");
                int ID = resultSet.getInt("ID");
                result.append(ID);
                result.append(" ");
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}
