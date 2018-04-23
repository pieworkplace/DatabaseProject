package servlet;

import database.JDBC.DBConnectionUtil;
import database.JDBC.DataProcessor;
import database.classes.Property;
import database.classes.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "OwnerSearchPropertyServlet", urlPatterns = "/OwnerSearchPropertyServlet")
public class OwnerSearchPropertyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String SearchItem = request.getParameter("SearchText");
        String SearchType = request.getParameter("SearchTypeText");
        User user = (User) request.getSession().getAttribute("user");
        String ownerName = user.getUsername();
        final List<Property> SearchOwnerRes = new ArrayList<>();
        if (!SearchItem.contains("~")) {
            DBConnectionUtil.update("CREATE VIEW MyProperties AS SELECT Name, Street, City, Zip, Size, Owner, ApprovedBy, PropertyType, IsPublic, IsCommercial, ID, AVG(Rating) AS AVG, COUNT(*) as CNT\n" +
                    "FROM (Property LEFT OUTER JOIN Visit ON ID = PropertyID) WHERE Owner = \""+ ownerName +"\"\n" +
                    "GROUP BY Name");
            DBConnectionUtil.select("SELECT * FROM MyProperties\n" +
                    "WHERE "+ SearchType +" LIKE \"%"+ SearchItem +"%\";", new DataProcessor() {
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
                            SearchOwnerRes.add(property);
                        }
                    }
                }
            });
            DBConnectionUtil.update("DROP VIEW cs4400_team_62.MyProperties RESTRICT;");
            request.getSession().setAttribute("myProperties", SearchOwnerRes);
            request.getRequestDispatcher("/ownerCenter.jsp").forward(request, response);
        }
        else {
            String[] nums = SearchItem.split("~");
//            int a = Integer.parseInt(nums[0]);
//            int b = Integer.parseInt(nums[1]);
            DBConnectionUtil.update("CREATE VIEW MyProperties AS SELECT Name, Street, City, Zip, Size, Owner, ApprovedBy, PropertyType, IsPublic, IsCommercial, ID, AVG(Rating) AS AVG, COUNT(*) as CNT\n" +
                    "FROM (Property LEFT OUTER JOIN Visit ON ID = PropertyID) WHERE Owner = \""+ ownerName +"\"\n" +
                    "GROUP BY Name");
            DBConnectionUtil.select("SELECT * FROM MyProperties\n" +
                    "WHERE ("+ SearchType +" >= "+ nums[0] +") AND ("+ SearchType +" <= "+ nums[1] +");", new DataProcessor() {
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
                            SearchOwnerRes.add(property);
                        }
                    }
                }
            });
            DBConnectionUtil.update("DROP VIEW cs4400_team_62.MyProperties RESTRICT;");
            request.getSession().setAttribute("myProperties", SearchOwnerRes);
            request.getRequestDispatcher("/ownerCenter.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
