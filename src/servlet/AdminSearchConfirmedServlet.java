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

@WebServlet(name = "AdminSearchConfirmedServlet", urlPatterns = "/AdminSearchConfirmedServlet")
public class AdminSearchConfirmedServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String SearchItem = request.getParameter("SearchText");
        String SearchType = request.getParameter("SearchTypeText");
        final List<Property> SearchConfirmedRes = new ArrayList<>();
        DBConnectionUtil.update("CREATE VIEW AllConfirmedProperties AS SELECT Name,Street, City, Zip, Size, PropertyType, IsPublic, IsCommercial, ID, ApprovedBy, AVG(Rating) AS AVG\n" +
                "FROM (Property JOIN Visit ON ID = PropertyID)\n" +
                "GROUP BY Name;");
        DBConnectionUtil.select("SELECT * FROM AllConfirmedProperties\n" +
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
                        String owner = "HaHa";
                        String approvedBy = resultSet.getString("ApprovedBy");
                        double avg_rating = resultSet.getDouble("AVG");
                        Property property = new Property(ID, name, size, isCommercial, isPublic, city, street, zip, propertyType, owner, approvedBy, avg_rating);
                        SearchConfirmedRes.add(property);
                    }
                }
            }
        });
        DBConnectionUtil.update("DROP VIEW cs4400_team_62.AllConfirmedProperties RESTRICT;");
        request.getSession().setAttribute("confirmedProperties", SearchConfirmedRes);
        request.getRequestDispatcher("/confirmedProperties.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
