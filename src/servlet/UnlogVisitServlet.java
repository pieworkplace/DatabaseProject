package servlet;

import database.classes.Property;
import database.classes.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UnlogVisitServlet", urlPatterns = "/UnlogVisitServlet")
public class UnlogVisitServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        Property property = (Property) request.getSession().getAttribute("Ireallyhatethis");
        UserService.unlogVisit(user.getUsername(), property.getID());
        request.getRequestDispatcher("/VisitorManageServlet").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
