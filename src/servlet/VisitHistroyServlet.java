package servlet;

import database.classes.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "VisitHistroyServlet", urlPatterns = "/VisitHistroyServlet")
public class VisitHistroyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userName = ((User) request.getSession().getAttribute("user")).getUsername();

        List<String> date = UserService.getDate(userName);
        request.getSession().setAttribute("date", date);

        List<String> propertyName = UserService.getPropertyName(userName);
        request.getSession().setAttribute("propertyName", propertyName);

        List<Integer> rating = UserService.getUserRating(userName);
        request.getSession().setAttribute("rating", rating);

        request.getRequestDispatcher("/visitHistory.jsp").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
