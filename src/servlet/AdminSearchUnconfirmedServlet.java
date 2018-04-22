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

@WebServlet(name = "AdminSearchUnconfirmedServlet", urlPatterns = "/AdminSearchUnconfirmedServlet")
public class AdminSearchUnconfirmedServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String SearchItem = request.getParameter("SearchText");
        String SearchType = request.getParameter("SearchTypeText");
        final List<Property> SearchUnconfirmedRes = new ArrayList<>();
        System.out.println(SearchItem);
        System.out.println(SearchType);
        DBConnectionUtil.update("CREATE VIEW AllUnconfirmedProperties AS SELECT Name, Street, City, Zip, Size, PropertyType, IsPublic, IsCommercial, ID, Owner\n" +
                "FROM Property\n" +
                "WHERE ApprovedBy IS NULL;");
        DBConnectionUtil.select("SELECT * FROM AllUnconfirmedProperties\n" +
                "WHERE "+ SearchType +" LIKE \"%"+ SearchItem +"%\";", new DataProcessor() {
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
                        SearchUnconfirmedRes.add(property);
                    }
                }
            }
        });
        DBConnectionUtil.update("DROP VIEW cs4400_team_62.AllUnconfirmedProperties RESTRICT;");
        request.getSession().setAttribute("unconfirmedProperties", SearchUnconfirmedRes);
        request.getRequestDispatcher("/unconfirmedProperties.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
