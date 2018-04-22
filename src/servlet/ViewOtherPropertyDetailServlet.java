package servlet;

import database.classes.Property;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ViewOtherPropertyDetailServlet", urlPatterns = "/ViewOtherPropertyDetailServlet" )
public class ViewOtherPropertyDetailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Property> propertyList = (List<Property>) request.getSession().getAttribute("allotherproperties");
        int index = Integer.parseInt(request.getParameter("rowIndex"));
        if (index >= propertyList.size() || index < 0){
            request.getRequestDispatcher("/propertyList.jsp").forward(request, response);
        } else{
            request.getSession().setAttribute("otherpropertyselected", propertyList.get(index));
            request.getRequestDispatcher("/otherPropertyDetail.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
