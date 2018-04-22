package servlet;

import database.JDBC.DBConnectionUtil;
import database.JDBC.DataProcessor;
import database.classes.FarmItem;
import service.UserService;

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

@WebServlet(name = "AdminSearchApprovedServlet", urlPatterns = "/AdminSearchApprovedServlet")
public class AdminSearchApprovedServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String SearchItem = request.getParameter("SearchNameText");
        String SearchType = request.getParameter("SearchTypeText");
//        System.out.println(SearchItem);
//        System.out.println(SearchType);
        final List<FarmItem> SearchItems = new ArrayList<>();
        DBConnectionUtil.select("SELECT Name, Type\n" +
                "FROM FarmItem\n" +
                "WHERE " + SearchType + " LIKE \"%" + SearchItem +"%\" AND isApproved = 1;", new DataProcessor() {
            @Override
            public void processData(ResultSet resultSet) throws SQLException {
                if (resultSet != null) {
                    while (resultSet.next()) {
                        String Name = resultSet.getString("Name");
                        boolean isApproved = true;
                        FarmItem.ItemType Type = FarmItem.stringToItemType(resultSet.getString("Type"));
                        FarmItem farmItem = new FarmItem(Name, isApproved, Type);
                        SearchItems.add(farmItem);
                    }
                }
            }
        });
        request.getSession().setAttribute("approvedItems", SearchItems);
        request.getRequestDispatcher("/approvedItems.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
