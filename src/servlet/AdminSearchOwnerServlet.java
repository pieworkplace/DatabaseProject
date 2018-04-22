package servlet;

import database.JDBC.DBConnectionUtil;
import database.JDBC.DataProcessor;
import database.classes.Visit;

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

@WebServlet(name = "AdminSearchOwnerServlet", urlPatterns = "/AdminSearchOwnerServlet")
public class AdminSearchOwnerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String SearchItem = request.getParameter("SearchText");
        String SearchType = request.getParameter("SearchTypeText");
        final List<Visit> SearchOwnerRes = new ArrayList<>();
        DBConnectionUtil.update("CREATE VIEW AllOwners AS SELECT Username, Email, COUNT(*) AS Num_Of_Properties\n" +
                "FROM (User JOIN Property ON Username = Owner)\n" +
                "GROUP BY Username;");
        DBConnectionUtil.select(
                "SELECT * FROM AllOwners\n" +
                        "WHERE "+ SearchType +" LIKE '%" + SearchItem + "%'", new DataProcessor() {
                    @Override
                    public void processData(ResultSet resultSet) throws SQLException {
                        if (resultSet != null) {
                            while (resultSet.next()) {
                                String Username = resultSet.getString("Username");
                                String Email = resultSet.getString("Email");
                                int Logged_visit = resultSet.getInt("Num_Of_Properties");
                                Visit visit = new Visit(Username, Email, Logged_visit);
                                SearchOwnerRes.add(visit);
                            }
                        }
                    }
                });
        DBConnectionUtil.update("DROP VIEW cs4400_team_62.AllOwners RESTRICT;");
        request.getSession().setAttribute("allOwners", SearchOwnerRes);
        request.getRequestDispatcher("/allOwners.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
