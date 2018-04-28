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
import java.util.Collections;
import java.util.Comparator;
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
        DBConnectionUtil.select("SELECT Name, Street, City, Zip, Size, Owner, ApprovedBy, PropertyType, IsPublic, IsCommercial, ID, AVG(Rating) AS AVG, COUNT(VisitDate) as CNT\n" +
                "      FROM (Property LEFT OUTER JOIN Visit ON ID = PropertyID) WHERE IsPublic = 1 AND ApprovedBy IS NOT NULL\n" +
                "      GROUP BY Name;", new DataProcessor() {
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
                        double avgRating = resultSet.getDouble("AVG");
                        int numOfVisits = resultSet.getInt("CNT");
                        Property property = new Property(ID, name, size, isCommercial, isPublic, city, street, zip, propertyType, owner, approvedBy, avgRating, numOfVisits);
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
        DBConnectionUtil.select("SELECT Name,Street, City, Zip, Size, Owner, PropertyType, IsPublic, IsCommercial, ID, ApprovedBy, AVG(Rating) AS AVG\n" +
                "FROM (Property LEFT OUTER JOIN Visit ON ID = PropertyID)\n" +
                "WHERE ApprovedBy IS NOT NULL\n" +
                "GROUP BY Name;", new DataProcessor() {
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
        DBConnectionUtil.select("SELECT Name, Street, City, Zip, Size, Owner, PropertyType, IsPublic, IsCommercial, ID, Owner\n" +
                "FROM Property\n" +
                "WHERE ApprovedBy IS NULL", new DataProcessor() {
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
                        String approvedBy = null;
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
//        "select * from Property where Owner=\"" + username + "\""
        DBConnectionUtil.select("SELECT Name, Street, City, Zip, Size, Owner, ApprovedBy, PropertyType, IsPublic, IsCommercial, ID, AVG(Rating) AS AVG, COUNT(VisitDate) as CNT\n" +
                "      FROM (Property LEFT OUTER JOIN Visit ON ID = PropertyID) WHERE Owner=\""+username+"\"" +
                "      GROUP BY Name;", new DataProcessor() {
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
                        int numberOfVisits = resultSet.getInt("CNT");
                        double avgRating = resultSet.getDouble("AVG");
                        Property property = new Property(ID, name, size, isCommercial, isPublic, city, street, zip, propertyType, owner, approvedBy, avgRating, numberOfVisits);
                        result.add(property);
                    }
                }
            }
        });
        return result;
    }


    public static List<Property> getOtherProperties(String username) {
        final List<Property> result = new ArrayList<>();
//        "select * from Property where not (Owner=\"" + username + "\" or ApprovedBy is NULL)"
        DBConnectionUtil.select("SELECT Name, Street, City, Zip, Size, Owner, ApprovedBy, PropertyType, IsPublic, IsCommercial, ID, AVG(Rating) AS AVG, COUNT(VisitDate) as CNT\n" +
                "      FROM (Property LEFT OUTER JOIN Visit ON ID = PropertyID) WHERE not (Owner=\""+username+"\" or ApprovedBy is NULL)" +
                "      GROUP BY Name;", new DataProcessor() {
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
                        int numberOfVisits = resultSet.getInt("CNT");
                        double avgRating = resultSet.getDouble("AVG");
                        Property property = new Property(ID, name, size, isCommercial, isPublic, city, street, zip, propertyType, owner, approvedBy, avgRating, numberOfVisits);
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

    public static String getOwnerEmail(String owner) {
        final String[] email = new String[1];
        DBConnectionUtil.select("select Email from User where Username=\"" + owner + "\"", new DataProcessor() {
            @Override
            public void processData(ResultSet resultSet) throws SQLException {
                while (resultSet.next()) {
                    email[0] = resultSet.getString("Email");
                }
            }
        });
        return email[0];

    }

    public static int getVisits(int id) {
        final int[] visits = new int[1];
        DBConnectionUtil.select("select count(PropertyID) as visits from Visit where PropertyID=" + id, new DataProcessor() {
            @Override
            public void processData(ResultSet resultSet) throws SQLException {
                while (resultSet.next()) {
                    visits[0] = resultSet.getInt("visits");
                }
            }
        });
        return visits[0];
    }

    public static List<Double> getRating(int id) {
        final List<Double> ratings = new ArrayList<>();
        DBConnectionUtil.select("select Rating from Visit where PropertyID=" + id, new DataProcessor() {
            @Override
            public void processData(ResultSet resultSet) throws SQLException {
                while (resultSet.next()) {
                    ratings.add(resultSet.getDouble("Rating"));
                }
            }
        });
        return ratings;
    }

    public static List<String> getItems(int id) {
        final List<String> items = new ArrayList<>();
        DBConnectionUtil.select("select ItemName from Has where PropertyID=" + id, new DataProcessor() {
            @Override
            public void processData(ResultSet resultSet) throws SQLException {
                while (resultSet.next()) {
                    items.add(resultSet.getString("ItemName"));
                }
            }
        });
        return items;
    }


    public static void addVisit(String userName, int id, String time, int rating) {
        DBConnectionUtil.update("insert into Visit values (\"" + userName  + "\", " + id + ", \"" + time + "\"," + rating + ");");
    }

    public static List<String> getDate(String userName) {
        final List<String> date = new ArrayList<>();
        DBConnectionUtil.select("select VisitDate from Visit where Username =\"" + userName + "\"", new DataProcessor() {
            @Override
            public void processData(ResultSet resultSet) throws SQLException {
                while (resultSet.next()) {
                    date.add(resultSet.getString("VisitDate"));
                }
            }
        });
        return date;
    }

    public static List<String> getPropertyName(String userName) {
        final List<String> propertyName = new ArrayList<>();
        DBConnectionUtil.select("SELECT Name\n" +
                "FROM (Property JOIN Visit ON ID =PropertyID)\n" +
                "WHERE Username =  \"" + userName + "\"", new DataProcessor() {
            @Override
            public void processData(ResultSet resultSet) throws SQLException {
                while (resultSet.next()) {
                    propertyName.add(resultSet.getString("Name"));
                }
            }
        });
        return propertyName;
    }

    public static List<Integer> getUserRating(String userName) {
        final List<Integer> rating = new ArrayList<>();
        DBConnectionUtil.select("select Rating from Visit where Username =\"" + userName + "\"", new DataProcessor() {
            @Override
            public void processData(ResultSet resultSet) throws SQLException {
                while (resultSet.next()) {
                    rating.add(resultSet.getInt("Rating"));
                }
            }
        });
        return rating;
    }

    public static boolean ifLogged(String username, int propertyID) {
        final boolean[] result = {false};
        DBConnectionUtil.select("select * from Visit where Username=\"" + username + "\" and PropertyID=" + propertyID, new DataProcessor() {
            @Override
            public void processData(ResultSet resultSet) throws SQLException {
                if (resultSet != null){
                    while (resultSet.next()){
                        result[0] = true;
                    }
                }
            }
        });
        return result[0];
    }

    public static void unlogVisit(String username, int id) {
        DBConnectionUtil.update("delete from Visit where Username=\"" + username + "\" and PropertyID=" + id);
    }

    public static List<FarmItem> getCropsInProperty(int ID) {
        final List<FarmItem> result = new ArrayList<>();
        DBConnectionUtil.select("select DISTINCT Name from (FarmItem join Has on ItemName=Name) where PropertyID="+ID+" and (not Type=\"ANIMAL\") and IsApproved=1", new DataProcessor() {
            @Override
            public void processData(ResultSet resultSet) throws SQLException {
                if (resultSet != null) {
                    while (resultSet.next()) {
                        String name = resultSet.getString("Name");
                        result.add(new FarmItem(name));
                    }
                }
            }
        });
        return result;
    }

    public static List<FarmItem> getAnimalsInProperty(int ID) {
        final List<FarmItem> result = new ArrayList<>();
        DBConnectionUtil.select("select DISTINCT Name from (FarmItem inner join Has on ItemName=Name) where PropertyID="+ID+" and ( Type=\"ANIMAL\") and IsApproved=1", new DataProcessor() {
            @Override
            public void processData(ResultSet resultSet) throws SQLException {
                if (resultSet != null) {
                    while (resultSet.next()) {
                        String name = resultSet.getString("Name");
                        result.add(new FarmItem(name));
                    }
                }
            }
        });
        return result;
    }

    public static String getAddress(String selectedName) {
        //System.out.println(selectedName);
        final String[] address = {new String()};
        DBConnectionUtil.select("select Street from Property where Name=\"" + selectedName + "\" ", new DataProcessor() {
            @Override
            public void processData(ResultSet resultSet) throws SQLException {
                //System.out.println("Testpoint1");
                if (resultSet != null) {
                    while (resultSet.next()) {
                        address[0] = resultSet.getString("Street");
                        //System.out.println("Testpoint2");
                    }
                }
            }
        });
        //System.out.println(address[0] + "test");
        return address[0];
    }

    public static String getCity(String selectedName) {
        final String[] city = {new String()};
        DBConnectionUtil.select("select City from Property where Name=\"" + selectedName + "\" ", new DataProcessor() {
            @Override
            public void processData(ResultSet resultSet) throws SQLException {
                if (resultSet != null) {
                    while (resultSet.next()) {
                        city[0] = resultSet.getString("City");
                    }
                }
            }
        });
        return city[0];
    }

    public static int getZip(String selectedName) {
        final int[] zip = new int[1];
        DBConnectionUtil.select("select Zip from Property where Name=\"" + selectedName + "\" ", new DataProcessor() {
            @Override
            public void processData(ResultSet resultSet) throws SQLException {
                if (resultSet != null) {
                    while (resultSet.next()) {
                        zip[0] = resultSet.getInt("Zip");
                    }
                }
            }
        });
        return zip[0];
    }

    public static double getSize(String selectedName) {
        final double[] size = new double[1];
        DBConnectionUtil.select("select Size from Property where Name=\"" + selectedName + "\" ", new DataProcessor() {
            @Override
            public void processData(ResultSet resultSet) throws SQLException {
                if (resultSet != null) {
                    while (resultSet.next()) {
                        size[0] = resultSet.getDouble("Size");
                    }
                }
            }
        });
        return size[0];
    }

    public static Property.PropertyType getType(String selectedName) {
        final Property.PropertyType[] type = new Property.PropertyType[1];
        DBConnectionUtil.select("select PropertyType from Property where Name=\"" + selectedName + "\" ", new DataProcessor() {
            @Override
            public void processData(ResultSet resultSet) throws SQLException {
                if (resultSet != null) {
                    while (resultSet.next()) {
                        type[0] = Property.stringToPropertyType(resultSet.getString("PropertyType"));
                    }
                }
            }
        });
        return type[0];
    }

    public static boolean getPublic(String selectedName) {
        final boolean[] type = new boolean[1];
        DBConnectionUtil.select("select IsPublic from Property where Name=\"" + selectedName + "\" ", new DataProcessor() {
            @Override
            public void processData(ResultSet resultSet) throws SQLException {
                if (resultSet != null) {
                    while (resultSet.next()) {
                        type[0] = resultSet.getBoolean("IsPublic");
                    }
                }
            }
        });
        return type[0];
    }

    public static boolean getCommercial(String selectedName) {
        final boolean[] type = new boolean[1];
        DBConnectionUtil.select("select IsCommercial from Property where Name=\"" + selectedName + "\" ", new DataProcessor() {
            @Override
            public void processData(ResultSet resultSet) throws SQLException {
                if (resultSet != null) {
                    while (resultSet.next()) {
                        type[0] = resultSet.getBoolean("IsCommercial");
                    }
                }
            }
        });
        return type[0];
    }

    public static int getID(String selectedName) {
        final int[] id = new int[1];
        DBConnectionUtil.select("select ID from Property where Name=\"" + selectedName + "\" ", new DataProcessor() {
            @Override
            public void processData(ResultSet resultSet) throws SQLException {
                if (resultSet != null) {
                    while (resultSet.next()) {
                        id[0] = resultSet.getInt("ID");
                    }
                }
            }
        });
        return id[0];
    }

    public static String getOwner(String selectedName) {
        final String[] owner = {new String()};
        DBConnectionUtil.select("select Owner from Property where Name=\"" + selectedName + "\" ", new DataProcessor() {
            @Override
            public void processData(ResultSet resultSet) throws SQLException {
                if (resultSet != null) {
                    while (resultSet.next()) {
                        owner[0] = resultSet.getString("Owner");
                    }
                }
            }
        });
        return owner[0];
    }

}

