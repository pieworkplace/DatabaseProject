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

@WebServlet(name = "HistoryToDetailServlet", urlPatterns = "/HistoryToDetailServlet")
public class HistoryToDetailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String selectedName = request.getParameter("selectedName");
        if (Integer.parseInt(request.getParameter("rowIndex")) < 0) {
            request.getRequestDispatcher("/visitHistory.jsp").forward(request, response);
            return;
        }
//        List<Property> propertyList = new ArrayList<>();
//        propertyList = (List<Property>) request.getSession().getAttribute("publicProperties");
//        if (index >= propertyList.size() || index < 0){
//            request.getRequestDispatcher("/visitorCenter.jsp").forward(request, response);
//        } else {
//        Property property = propertyList.get(index);

//        request.getSession().setAttribute("Ireallyhatethis", property);


        String address = UserService.getAddress(selectedName);
        String city = UserService.getCity(selectedName);
        int zip = UserService.getZip(selectedName);
        double size = UserService.getSize(selectedName);
        Property.PropertyType type = UserService.getType(selectedName);
        boolean isPublic = UserService.getPublic(selectedName);
        boolean isCommercial = UserService.getCommercial(selectedName);
        int id = UserService.getID(selectedName);
        String owner = UserService.getOwner(selectedName);

//        System.out.println(address +" addressTest");
//        System.out.println(zip +" zip Test");
//        System.out.println(size +" size Test");
//        System.out.println(isPublic +" publicTest");


//        System.out.println(address +"addressTest");
//        System.out.println(city +"cityTest");
//        System.out.println(zip);

        Property property = new Property(id, selectedName, size, isCommercial, isPublic, city, address, zip, type,  owner, "", 0,  0);
        request.getSession().setAttribute("Ireallyhatethis", property);



        String ownerEmail = UserService.getOwnerEmail(owner);
        request.getSession().setAttribute("ownerEmail", ownerEmail);

        int visits = UserService.getVisits(id);
        request.getSession().setAttribute("visits", visits);

        List<Double> ratings = UserService.getRating(id);
        request.getSession().setAttribute("ratings", ratings);

        List<String> items = UserService.getItems(id);
        request.getSession().setAttribute("items", items);

        request.getRequestDispatcher("/gardenRatingVisitor.jsp").forward(request, response);
//        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
