package servlet;

import database.classes.Property;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "VisitorManageServlet", urlPatterns = "/VisitorManageServlet")
public class VisitorManageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int index = Integer.parseInt(request.getParameter("rowIndex"));
        List<Property> propertyList = new ArrayList<>();
        propertyList = (List<Property>) request.getSession().getAttribute("publicProperties");
        request.getSession().setAttribute("Ireallyhatethis", propertyList.get(index));
        request.getRequestDispatcher("/gardenRating.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
