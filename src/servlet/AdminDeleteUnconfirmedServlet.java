package servlet;

import database.JDBC.DBConnectionUtil;
import database.classes.Property;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminDeleteUnconfirmedServlet", urlPatterns = "/AdminDeleteUnconfirmedServlet")
public class AdminDeleteUnconfirmedServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Property property = (Property) request.getSession().getAttribute("unconfirmedpropertyselected");
        DBConnectionUtil.update("delete from Has where PropertyID="+property.getID());
        DBConnectionUtil.update("delete from Property where Name=\""+property.getName()+"\"");
        DBConnectionUtil.update("delete from Visit where PropertyID="+property.getID());
        List<Property> propertyList= UserService.getconfirmedProperties();
        request.getSession().setAttribute("unconfirmedProperties", propertyList);
        request.getRequestDispatcher("/unconfirmedProperties.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
