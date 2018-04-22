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

@WebServlet(name = "AdminOwnerServlet", urlPatterns = "/AdminOwnerServlet")
public class AdminOwnerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int index = Integer.parseInt(request.getParameter("rowIndex"));
//        System.out.println(index);
        List<Visit> allVisitors = (List<Visit>) request.getSession().getAttribute("allOwners");
        if (index < 0 || index >= allVisitors.size()) {
            request.getRequestDispatcher("/allOwners.jsp").forward(request, response);
        }
        String selected = allVisitors.get(index).getUsername();
        DBConnectionUtil.update("DELETE FROM User\n" +
                "WHERE Username = \"" + selected +"\"");
        List<Visit> allOwners_update = UserService.getAllOwners();
        request.getSession().setAttribute("allOwners", allOwners_update);
        request.getRequestDispatcher("/allOwners.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
