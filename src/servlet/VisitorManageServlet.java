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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "VisitorManageServlet", urlPatterns = "/VisitorManageServlet")
public class VisitorManageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Property property = null;
        if (request.getParameter("rowIndex") != null){
            int index = Integer.parseInt(request.getParameter("rowIndex"));
            List<Property> propertyList = new ArrayList<>();
            propertyList = (List<Property>) request.getSession().getAttribute("publicProperties");
            if (index >= propertyList.size() || index < 0){
                request.getRequestDispatcher("/visitorCenter.jsp").forward(request, response);
            } else{
                property = propertyList.get(index);
                request.getSession().setAttribute("Ireallyhatethis", property);}}
        else{
           property = (Property) request.getSession().getAttribute("Ireallyhatethis");
        }

        String ownerEmail = UserService.getOwnerEmail(property.getOwner());
        request.getSession().setAttribute("ownerEmail", ownerEmail);

        List<String> items = UserService.getItems(property.getID());
        request.getSession().setAttribute("items", items);

        int visits = UserService.getVisits(property.getID());
        request.getSession().setAttribute("visits", visits);

        List<Double> ratings = UserService.getRating(property.getID());
        request.getSession().setAttribute("ratings", ratings);

        User user = (User) request.getSession().getAttribute("user");
        String username = user.getUsername();
        int propertyID = property.getID();

        if (UserService.ifLogged(username, propertyID)){
            request.getRequestDispatcher("/gardenRatingVisitor.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/gardenRating.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
