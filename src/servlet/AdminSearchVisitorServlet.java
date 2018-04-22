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

@WebServlet(name = "AdminSearchVisitorServlet", urlPatterns = "/AdminSearchVisitorServlet")
public class AdminSearchVisitorServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String SearchItem = request.getParameter("SearchText");
        String SearchType = request.getParameter("SearchTypeText");
        final List<Visit> SearchRes = new ArrayList<>();
        DBConnectionUtil.update("CREATE VIEW AllVisitors AS SELECT Username, Email, COUNT(*)AS Visits\n" +
                "      FROM (User NATURAL JOIN Visit)\n" +
                "      GROUP BY Username");
        DBConnectionUtil.select(
                "SELECT * FROM AllVisitors\n" +
                        "WHERE "+ SearchType +" LIKE '%" + SearchItem + "%'", new DataProcessor() {
                    @Override
                    public void processData(ResultSet resultSet) throws SQLException {
                        if (resultSet != null) {
                            while (resultSet.next()) {
                                String Username = resultSet.getString("Username");
                                String Email = resultSet.getString("Email");
                                int Logged_visit = resultSet.getInt("Visits");
                                Visit visit = new Visit(Username, Email, Logged_visit);
                                SearchRes.add(visit);
                            }
                        }
                    }
                });
        DBConnectionUtil.update("DROP VIEW cs4400_team_62.AllVisitors RESTRICT;");
        request.getSession().setAttribute("allVisitors", SearchRes);
        request.getRequestDispatcher("/allVisitors.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
