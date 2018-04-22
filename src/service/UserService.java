package service;

import database.JDBC.DBConnectionUtil;
import database.JDBC.DataProcessor;
import database.JDBC.MD5Util;
import database.JDBC.SQLPreparator;
import database.classes.FarmItem;
import database.classes.Property;
import database.classes.User;
import database.classes.Visit;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
                        user.setEmail(resultSet.getString("Email"));
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

    public static List<FarmItem> getAnimalList(){
        final List<FarmItem> result = new ArrayList<>();
        DBConnectionUtil.select("select * from FarmItem where IsApproved = 1 and Type = 'ANIMAL'", new DataProcessor() {
            @Override
            public void processData(ResultSet resultSet) throws SQLException {
                if (resultSet != null){
                    while (resultSet.next()){
                        String name = resultSet.getString("Name");
                        boolean isApproved = resultSet.getBoolean("IsApproved");
                        FarmItem.ItemType itemType = FarmItem.stringToItemType(resultSet.getString("Type"));
                        result.add(new FarmItem(name, isApproved, itemType));
                    }
                }
            }
        });
        return result;
    }

    public static List<FarmItem> getOrchardList(){
        final List<FarmItem> result = new ArrayList<>();
        DBConnectionUtil.select("select * from FarmItem where IsApproved = 1 and (Type = 'NUT' or Type = 'FRUIT')", new DataProcessor() {
            @Override
            public void processData(ResultSet resultSet) throws SQLException {
                if (resultSet != null){
                    while (resultSet.next()){
                        String name = resultSet.getString("Name");
                        boolean isApproved = resultSet.getBoolean("IsApproved");
                        FarmItem.ItemType itemType = FarmItem.stringToItemType(resultSet.getString("Type"));
                        result.add(new FarmItem(name, isApproved, itemType));
                    }
                }
            }
        });

        return result;
    }

    public static List<FarmItem> getGardenList(){
        final List<FarmItem> result = new ArrayList<>();
        DBConnectionUtil.select("select * from FarmItem where IsApproved = 1 and (Type = 'VEGETABLE' or Type = 'FLOWER')", new DataProcessor() {
            @Override
            public void processData(ResultSet resultSet) throws SQLException {
                if (resultSet != null){
                    while (resultSet.next()){
                        String name = resultSet.getString("Name");
                        boolean isApproved = resultSet.getBoolean("IsApproved");
                        FarmItem.ItemType itemType = FarmItem.stringToItemType(resultSet.getString("Type"));
                        result.add(new FarmItem(name, isApproved, itemType));
                    }
                }
            }
        });
        return result;
    }

    public static List<FarmItem> getCropList(){
        final List<FarmItem> result = new ArrayList<>();
        DBConnectionUtil.select("select * from FarmItem where IsApproved = 1 and (not Type = 'ANIMAL')", new DataProcessor() {
            @Override
            public void processData(ResultSet resultSet) throws SQLException {
                if (resultSet != null){
                    while (resultSet.next()){
                        String name = resultSet.getString("Name");
                        boolean isApproved = resultSet.getBoolean("IsApproved");
                        FarmItem.ItemType itemType = FarmItem.stringToItemType(resultSet.getString("Type"));
                        result.add(new FarmItem(name, isApproved, itemType));
                    }
                }
            }
        });
        return result;
    }

    public static List<Property> getPublicProperties() {
        final List<Property> result = new ArrayList<>();
        DBConnectionUtil.select("select * from Property where IsPublic = 1", new DataProcessor() {
            @Override
            public void processData(ResultSet resultSet) throws SQLException {
                if (resultSet != null){
                    while (resultSet.next()){
                        int ID = resultSet.getInt("ID");
                        String name = resultSet.getString("Name");
                        double size = resultSet.getDouble("Size");
                        boolean isCommercial = resultSet.getBoolean("IsCommercial");
                        boolean isPublic = resultSet.getBoolean("IsPublic");
                        String city = resultSet.getString("City");
                        String street = resultSet.getString("Street");
                        int zip = resultSet.getInt("Zip");
                        Property.PropertyType propertyType = Property.stringToPropertyType(resultSet.getString("PropertyType"));
                        String owner = resultSet.getString("Owner");
                        String approvedBy = resultSet.getString("ApprovedBy");
                        Property property = new Property(ID, name, size, isCommercial, isPublic, city, street, zip, propertyType, owner, approvedBy);
                        result.add(property);
                    }
                }
            }
        });
        return result;
    }
    public static List<Visit> getAllVisitors() {
        final List<Visit> res = new ArrayList<>();
        DBConnectionUtil.select("SELECT Username, Email, COUNT(*) AS Visits\n" +
                "FROM (User NATURAL JOIN Visit)\n" +
                "GROUP BY Username;", new DataProcessor() {
            @Override
            public void processData(ResultSet resultSet) throws SQLException {
                if (resultSet != null) {
                    while (resultSet.next()) {
                        String Username = resultSet.getString("Username");
                        String Email = resultSet.getString("Email");
                        int Logged_visit = resultSet.getInt("Visits");
                        Visit visit = new Visit(Username, Email, Logged_visit);
                        res.add(visit);
                    }
                }
            }
        });
        return res;
    }

    public static List<Visit> getAllOwners() {
        final List<Visit> res = new ArrayList<>();
        DBConnectionUtil.select("SELECT Username, Email, COUNT(*) AS Num_Of_Properties\n" +
                "FROM (User JOIN Property ON Username = Owner)\n" +
                "GROUP BY Username;\n", new DataProcessor() {
            @Override
            public void processData(ResultSet resultSet) throws SQLException {
                if (resultSet != null) {
                    while (resultSet.next()) {
                        String Username = resultSet.getString("Username");
                        String Email = resultSet.getString("Email");
                        int Number = resultSet.getInt("Num_Of_Properties");
                        Visit visit = new Visit(Username, Email, Number);
                        res.add(visit);
                    }
                }
            }
        });
        return res;
    }

    public static List<Property> getconfirmedProperties() {
        final List<Property> result = new ArrayList<>();
        DBConnectionUtil.select("SELECT Name,Street, City, Zip, Size, PropertyType, IsPublic, IsCommercial, ID, ApprovedBy, AVG(Rating) AS AVG\n" +
                "FROM (Property JOIN Visit ON ID = PropertyID)\n" +
                "GROUP BY Username;", new DataProcessor() {
            @Override
            public void processData(ResultSet resultSet) throws SQLException {
                if (resultSet != null){
                    while (resultSet.next()){
                        int ID = resultSet.getInt("ID");
                        String name = resultSet.getString("Name");
                        double size = resultSet.getDouble("Size");
                        boolean isCommercial = resultSet.getBoolean("IsCommercial");
                        boolean isPublic = resultSet.getBoolean("IsPublic");
                        String city = resultSet.getString("City");
                        String street = resultSet.getString("Street");
                        int zip = resultSet.getInt("Zip");
                        Property.PropertyType propertyType = Property.stringToPropertyType(resultSet.getString("PropertyType"));
                        String owner = "HaHa";
                        String approvedBy = resultSet.getString("ApprovedBy");
                        double avg_rating = resultSet.getDouble("AVG");
                        Property property = new Property(ID, name, size, isCommercial, isPublic, city, street, zip, propertyType, owner, approvedBy, avg_rating);
                        result.add(property);
                    }
                }
            }
        });
        return result;
    }

    public static List<Property> getunconfirmedProperties() {
        final List<Property> result = new ArrayList<>();
        DBConnectionUtil.select("SELECT Name, Street, City, Zip, Size, PropertyType, IsPublic, IsCommercial, ID, Owner\n" +
                "FROM Property\n" +
                "WHERE IsPublic = FALSE;", new DataProcessor() {
            @Override
            public void processData(ResultSet resultSet) throws SQLException {
                if (resultSet != null){
                    while (resultSet.next()){
                        int ID = resultSet.getInt("ID");
                        String name = resultSet.getString("Name");
                        double size = resultSet.getDouble("Size");
                        boolean isCommercial = resultSet.getInt("IsCommercial") == 1;
                        boolean isPublic = resultSet.getInt("IsPublic") == 1;
                        String city = resultSet.getString("City");
                        String street = resultSet.getString("Street");
                        int zip = resultSet.getInt("Zip");
                        Property.PropertyType propertyType = Property.stringToPropertyType(resultSet.getString("PropertyType"));
                        String owner = resultSet.getString("Owner");
                        String approvedBy = "XiXi";
                        double avg_rating = 0;
                        Property property = new Property(ID, name, size, isCommercial, isPublic, city, street, zip, propertyType, owner, approvedBy, avg_rating);
                        result.add(property);
                    }
                }
            }
        });
        return result;
    }

    public static List<FarmItem> getapprovedItems() {
        final List<FarmItem> res = new ArrayList<>();
        DBConnectionUtil.select("SELECT Name, Type\n" +
                "FROM FarmItem\n" +
                "WHERE IsApproved = TRUE;", new DataProcessor() {
            @Override
            public void processData(ResultSet resultSet) throws SQLException {
                if (resultSet != null) {
                    while (resultSet.next()) {
                        String Name = resultSet.getString("Name");
                        boolean isApproved = true;
                        FarmItem.ItemType Type = FarmItem.stringToItemType(resultSet.getString("Type"));
                        FarmItem farmItem = new FarmItem(Name, isApproved, Type);
                        res.add(farmItem);
                    }
                }
            }
        });
        return res;
    }

    public static List<FarmItem> getpendingItems() {
        final List<FarmItem> res = new ArrayList<>();
        DBConnectionUtil.select("SELECT Name, Type\n" +
                "FROM FarmItem\n" +
                "WHERE IsApproved = FALSE;", new DataProcessor() {
            @Override
            public void processData(ResultSet resultSet) throws SQLException {
                if (resultSet != null) {
                    while (resultSet.next()) {
                        String Name = resultSet.getString("Name");
                        boolean isApproved = false;
                        FarmItem.ItemType Type = FarmItem.stringToItemType(resultSet.getString("Type"));
                        FarmItem farmItem = new FarmItem(Name, isApproved, Type);
                        res.add(farmItem);
                    }
                }
            }
        });
        return res;
    }

    public static List<Property> getMyProperties(String username) {
        final List<Property> result = new ArrayList<>();
        DBConnectionUtil.select("select * from Property where Owner=\"" + username + "\"", new DataProcessor() {
            @Override
            public void processData(ResultSet resultSet) throws SQLException {
                if (resultSet != null) {
                    while (resultSet.next()) {
                        int ID = resultSet.getInt("ID");
                        String name = resultSet.getString("Name");
                        double size = resultSet.getDouble("Size");
                        boolean isCommercial = resultSet.getBoolean("IsCommercial");
                        boolean isPublic = resultSet.getBoolean("IsPublic");
                        String city = resultSet.getString("City");
                        String street = resultSet.getString("Street");
                        int zip = resultSet.getInt("Zip");
                        Property.PropertyType propertyType = Property.stringToPropertyType(resultSet.getString("PropertyType"));
                        String owner = resultSet.getString("Owner");
                        String approvedBy = resultSet.getString("ApprovedBy");
                        Property property = new Property(ID, name, size, isCommercial, isPublic, city, street, zip, propertyType, owner, approvedBy);
                        result.add(property);
                    }
                }
            }
        });
        return result;
    }


    public static List<Property> getOtherProperties(String username) {
        final List<Property> result = new ArrayList<>();
        DBConnectionUtil.select("select * from Property where not (Owner=\"" + username + "\" or ApprovedBy is NULL)", new DataProcessor() {
            @Override
            public void processData(ResultSet resultSet) throws SQLException {
                if (resultSet != null) {
                    while (resultSet.next()) {
                        int ID = resultSet.getInt("ID");
                        String name = resultSet.getString("Name");
                        double size = resultSet.getDouble("Size");
                        boolean isCommercial = resultSet.getBoolean("IsCommercial");
                        boolean isPublic = resultSet.getBoolean("IsPublic");
                        String city = resultSet.getString("City");
                        String street = resultSet.getString("Street");
                        int zip = resultSet.getInt("Zip");
                        Property.PropertyType propertyType = Property.stringToPropertyType(resultSet.getString("PropertyType"));
                        String owner = resultSet.getString("Owner");
                        String approvedBy = resultSet.getString("ApprovedBy");
                        Property property = new Property(ID, name, size, isCommercial, isPublic, city, street, zip, propertyType, owner, approvedBy);
                        result.add(property);
                    }
                }
            }
        });
        return result;
    }

    public static List<FarmItem> getItemsInProperty(int ID) {
        final List<FarmItem> result = new ArrayList<>();
        DBConnectionUtil.select("select * from Has where PropertyID="+ID, new DataProcessor() {
            @Override
            public void processData(ResultSet resultSet) throws SQLException {
                if (resultSet != null) {
                    while (resultSet.next()) {
                        String name = resultSet.getString("ItemName");
                        result.add(new FarmItem(name));
                    }
                }
            }
        });
        return result;
    }



}

