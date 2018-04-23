package servlet;

import database.JDBC.DBConnectionUtil;
import database.JDBC.DataProcessor;
import database.classes.Property;

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

@WebServlet(name = "VisitorSearchServlet", urlPatterns = "/VisitorSearchServlet")
public class VisitorSearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String SearchItem = request.getParameter("SearchText");
        String SearchType = request.getParameter("SearchTypeText");
        final List<Property> SearchVisitorRes = new ArrayList<>();
        if (!SearchItem.contains("~")) {
            System.out.println("Fuck You");
            DBConnectionUtil.update("CREATE VIEW PublicProperties AS SELECT Name, Street, City, Zip, Size, Owner, ApprovedBy, PropertyType, IsPublic, IsCommercial, ID, AVG(Rating) AS AVG, COUNT(*) as CNT\n" +
                    "FROM (Property LEFT OUTER JOIN Visit ON ID = PropertyID) WHERE IsPublic = 1 AND ApprovedBy IS NOT NULL\n" +
                    "GROUP BY Name;");
            DBConnectionUtil.select("SELECT * FROM PublicProperties\n"
                    + "WHERE "+ SearchType +" LIKE \"%"+ SearchItem +"%\";", new DataProcessor() {
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
                            SearchVisitorRes.add(property);
                        }
                    }
                }
            });
            DBConnectionUtil.update("DROP VIEW cs4400_team_62.PublicProperties RESTRICT;");
            request.getSession().setAttribute("publicProperties", SearchVisitorRes);
            request.getRequestDispatcher("/visitorCenter.jsp").forward(request, response);
        }
        else {
            String[] nums = SearchItem.split("~");
            DBConnectionUtil.update("CREATE VIEW PublicProperties AS SELECT Name, Street, City, Zip, Size, Owner, ApprovedBy, PropertyType, IsPublic, IsCommercial, ID, AVG(Rating) AS AVG, COUNT(*) as CNT\n" +
                    "FROM (Property LEFT OUTER JOIN Visit ON ID = PropertyID) WHERE IsPublic = 1 AND ApprovedBy IS NOT NULL\n" +
                    "GROUP BY Name");
            DBConnectionUtil.select("SELECT * FROM PublicProperties\n" +
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
                            SearchVisitorRes.add(property);
                        }
                    }
                }
            });
            DBConnectionUtil.update("DROP VIEW cs4400_team_62.PublicProperties RESTRICT;");
            request.getSession().setAttribute("publicProperties", SearchVisitorRes);
            request.getRequestDispatcher("/visitorCenter.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
