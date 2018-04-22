package servlet;

import database.JDBC.DBConnectionUtil;
import database.classes.Visit;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminVisitorServlet", urlPatterns = "/AdminVisitorServlet")
public class AdminVisitorServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int index = Integer.parseInt(request.getParameter("rowIndexforVA"));
//        System.out.println(index);
        List<Visit> allVisitors = (List<Visit>) request.getSession().getAttribute("allVisitors");
        if (index < 0 || index >= allVisitors.size()) {
            request.getRequestDispatcher("/allVisitors.jsp").forward(request, response);
        }
        String selected = allVisitors.get(index).getUsername();
        DBConnectionUtil.update("DELETE FROM User\n" +
                "WHERE Username = \"" + selected +"\"");
        List<Visit> allVisitors_update = UserService.getAllVisitors();
        request.getSession().setAttribute("allVisitors", allVisitors_update);
        request.getRequestDispatcher("/allVisitors.jsp").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
