package servlet;

import database.JDBC.DBConnectionUtil;
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

@WebServlet(name = "OwnerDeleteServlet", urlPatterns = "/OwnerDeleteServlet")
public class OwnerDeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Property property = (Property) request.getSession().getAttribute("ownerpropertyselected");
        DBConnectionUtil.update("delete from Has where PropertyID="+property.getID());
        DBConnectionUtil.update("delete from Property where Name=\""+property.getName()+"\"");
        DBConnectionUtil.update("delete from Visit where PropertyID="+property.getID());
        User user = (User) request.getSession().getAttribute("user");
        List<Property> propertyList= UserService.getMyProperties(user.getUsername());
        request.getSession().setAttribute("myProperties", propertyList);
        request.getRequestDispatcher("/ownerCenter.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
