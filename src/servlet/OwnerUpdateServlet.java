package servlet;

import database.JDBC.DBConnectionUtil;
import database.JDBC.DataProcessor;
import database.classes.Property;
import database.classes.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "OwnerUpdateServlet", urlPatterns = "/OwnerUpdateServlet")
public class OwnerUpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Property property = (Property) request.getSession().getAttribute("ownerpropertyselected");
        String propertyName = request.getParameter("propertyName");
        String street = request.getParameter("streetAddress");
        String city = request.getParameter("city");
        int isCommercial = request.getParameter("isCommercial").equals("True")? 1 : 0;
        int isPublic = request.getParameter("isPublic").equals("True")? 1 : 0;
        int zip = Integer.parseInt(request.getParameter("zip"));
        double acres = Double.parseDouble(request.getParameter("acres"));
        String username = ((User)request.getSession().getAttribute("user")).getUsername();

        int line2 = DBConnectionUtil.update("update Property set Name=\""+propertyName+"\",Size="+acres+",IsCommercial="+isCommercial+",isPublic="+isPublic+",Street=\""+street+"\",City=\""+city+"\",Zip="+zip+",ApprovedBy=null where ID="+property.getID());
        if (line2 == 0){
            request.getSession().setAttribute("updatePropertyFail", true);
            System.out.println(request.getSession().getAttribute("updatePropertyFail"));
            request.getRequestDispatcher("/manageProperties.jsp").forward(request, response);
            return;
        }
        User user = (User) request.getSession().getAttribute("user");
        request.getSession().setAttribute("myProperties", UserService.getMyProperties(user.getUsername()));
        request.getRequestDispatcher("/ownerCenter.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
