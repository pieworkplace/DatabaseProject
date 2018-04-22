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

@WebServlet(name = "ViewOtherPropertyServlet", urlPatterns = "/ViewOtherPropertyServlet")
public class ViewOtherPropertyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = ((User)request.getSession().getAttribute("user")).getUsername();
        List<Property> propertyList = UserService.getOtherProperties(username);
        request.getSession().setAttribute("allotherproperties", propertyList);
        request.getRequestDispatcher("/propertyList.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
