package servlet;

import database.classes.Property;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminManageUnconfirmedServlet", urlPatterns = "/AdminManageUnconfirmedServlet")
public class AdminManageUnconfirmedServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int index = Integer.parseInt(request.getParameter("rowIndex"));
        List<Property> propertyList = (List<Property>) request.getSession().getAttribute("unconfirmedProperties");
        if (index >= propertyList.size() || index < 0){
            request.getRequestDispatcher("/unconfirmedProperties.jsp").forward(request, response);
        } else{
            request.getSession().setAttribute("unconfirmedpropertyselected", propertyList.get(index));
            request.getRequestDispatcher("/manageUnconfirmedProperty.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}