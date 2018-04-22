package servlet;

import database.JDBC.DBConnectionUtil;
import database.JDBC.DataProcessor;
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
        String propertyName = request.getParameter("propertyName");
        String street = request.getParameter("streetAddress");
        String city = request.getParameter("city");
        int isCommercial = request.getParameter("isCommercial").equals("Yes")? 1 : 0;
        int isPublic = request.getParameter("isPublic").equals("Yes")? 1 : 0;
        String propertyType = request.getParameter("propertyType");
        int zip = Integer.parseInt(request.getParameter("zip"));
        double acres = Double.parseDouble(request.getParameter("acres"));
        String username = ((User)request.getSession().getAttribute("user")).getUsername();
        int line2 = DBConnectionUtil.update("insert into Property values(null,\""+propertyName+"\","+acres+","+isCommercial+","+isPublic+",\""+street+"\",\""+city+"\","+zip+",\""+propertyType+"\",\""+username+"\", null)");
        if (line2 == 0){
            request.getSession().setAttribute("addNewPropertyFail", true);
            System.out.println(request.getSession().getAttribute("addNewPropertyFail"));
            request.getRequestDispatcher("/addProperty.jsp").forward(request, response);
            return;
        }
        final StringBuilder propertyID = new StringBuilder();
        DBConnectionUtil.select("select * from Property where Name=\"" + propertyName + "\"", new DataProcessor() {
            @Override
            public void processData(ResultSet resultSet) throws SQLException {
                while (resultSet.next()){
                    propertyID.append(resultSet.getInt("ID"));
                }
            }
        });
        DBConnectionUtil.update("insert into Has values("+propertyID.toString()+",\""+request.getParameter("cropSelect")+"\")");
        if( propertyType.equals("FARM")){
            DBConnectionUtil.update("insert into Has values("+propertyID.toString()+",\""+request.getParameter("animalSelect")+"\")");
        }
        User user = (User) request.getSession().getAttribute("user");
        request.getSession().setAttribute("myProperties", UserService.getMyProperties(user.getUsername()));
        request.getRequestDispatcher("/ownerCenter.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
