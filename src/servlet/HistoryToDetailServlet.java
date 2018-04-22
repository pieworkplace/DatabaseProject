package servlet;

import database.classes.Property;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "HistoryToDetailServlet", urlPatterns = "/HistoryToDetailServlet")
public class HistoryToDetailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int index = Integer.parseInt(request.getParameter("rowIndex"));
        List<Property> propertyList = new ArrayList<>();
        propertyList = (List<Property>) request.getSession().getAttribute("publicProperties");
        if (index >= propertyList.size() || index < 0){
            request.getRequestDispatcher("/visitorCenter.jsp").forward(request, response);
        } else {
            Property property = propertyList.get(index);
            request.getSession().setAttribute("Ireallyhatethis", property);

            String ownerEmail = UserService.getOwnerEmail(property.getOwner());
            request.getSession().setAttribute("ownerEmail", ownerEmail);

            int visits = UserService.getVisits(property.getID());
            request.getSession().setAttribute("visits", visits);

            List<Double> ratings = UserService.getRating(property.getID());
            request.getSession().setAttribute("ratings", ratings);

            List<String> items = UserService.getItems(property.getID());
            request.getSession().setAttribute("items", items);

            request.getRequestDispatcher("/gardenRating.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
