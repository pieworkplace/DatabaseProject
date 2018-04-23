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

@WebServlet(name = "AdminDeleteConfirmedServlet", urlPatterns = "/AdminDeleteConfirmedServlet")
public class AdminDeleteConfirmedServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Property property = (Property) request.getSession().getAttribute("confirmedpropertyselected");
        DBConnectionUtil.update("delete from Has where PropertyID="+property.getID());
        DBConnectionUtil.update("delete from Property where Name=\""+property.getName()+"\"");
        List<Property> propertyList= UserService.getconfirmedProperties();
        request.getSession().setAttribute("confirmedProperties", propertyList);
        request.getRequestDispatcher("/confirmedProperties.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
