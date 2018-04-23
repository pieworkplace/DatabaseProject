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

@WebServlet(name = "OwnerSearchOtherPropertyServlet", urlPatterns = "/OwnerSearchOtherPropertyServlet")
public class OwnerSearchOtherPropertyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String SearchItem = request.getParameter("SearchText");
        String SearchType = request.getParameter("SearchTypeText");
        User user = (User) request.getSession().getAttribute("user");
        String ownerName = user.getUsername();
        final List<Property> SearchOtherOwnerRes = new ArrayList<>();
        if (!SearchItem.contains("~")) {
            DBConnectionUtil.update("CREATE VIEW OtherProperties AS SELECT Name, Street, City, Zip, Size, Owner, ApprovedBy, PropertyType, IsPublic, IsCommercial, ID, AVG(Rating) AS AVG, COUNT(*) as CNT\n" +
                    "FROM (Property LEFT OUTER JOIN Visit ON ID = PropertyID) WHERE not (Owner = \""+ ownerName +"\" or ApprovedBy is NULL)\n" +
                    "GROUP BY Name;");
            DBConnectionUtil.select("SELECT * FROM OtherProperties\n" +
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
                            SearchOtherOwnerRes.add(property);
                        }
                    }
                }
            });
            DBConnectionUtil.update("DROP VIEW cs4400_team_62.OtherProperties RESTRICT;");
            request.getSession().setAttribute("allotherproperties", SearchOtherOwnerRes);
            request.getRequestDispatcher("/propertyList.jsp").forward(request, response);
        }
        else {
            String[] nums = SearchItem.split("~");
            DBConnectionUtil.update("CREATE VIEW OtherProperties AS SELECT Name, Street, City, Zip, Size, Owner, ApprovedBy, PropertyType, IsPublic, IsCommercial, ID, AVG(Rating) AS AVG, COUNT(*) as CNT\n" +
                    "FROM (Property LEFT OUTER JOIN Visit ON ID = PropertyID) WHERE not (Owner = \""+ ownerName +"\" or ApprovedBy is NULL)\n" +
                    "GROUP BY Name;");
            DBConnectionUtil.select("SELECT * FROM OtherProperties\n" +
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
                            SearchOtherOwnerRes.add(property);
                        }
                    }
                }
            });
            DBConnectionUtil.update("DROP VIEW cs4400_team_62.OtherProperties RESTRICT;");
            request.getSession().setAttribute("allotherproperties", SearchOtherOwnerRes);
            request.getRequestDispatcher("/propertyList.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
