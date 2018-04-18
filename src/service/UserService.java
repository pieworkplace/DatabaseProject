package service;

import database.JDBC.DBConnectionUtil;
import database.JDBC.DataProcessor;
import database.JDBC.MD5Util;
import database.JDBC.SQLPreparator;
import database.classes.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {
    public static User login(final String email, final String password) {
        final User user = new User();
        if (email.length() == 0 || password.length() == 0){
            return null;
        }
        DBConnectionUtil.preSelect("select * from User where Email = ? and Hashed_Password = ?", new SQLPreparator() {
            @Override
            public void prepareSQL(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, MD5Util.crypt(password));
            }
        }, new DataProcessor() {
            @Override
            public void processData(ResultSet resultSet) throws SQLException {
                if (resultSet != null) {
                    while (resultSet.next()) {
                        user.setUsername(resultSet.getString("Username"));
                        user.setUserType(User.stringToUserType(resultSet.getString("Usertype")));
                    }
                }
            }
        });
        if (user.getUsername() == null){
            return null;
        }else{
            return user;
        }
    }
}
