package servlet;

import database.JDBC.DBConnectionUtil;
import database.JDBC.DataProcessor;
import database.JDBC.MD5Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "OwnerRegisterServlet", urlPatterns = "/OwnerRegisterServlet")
public class OwnerRegisterServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String passwordAgain = request.getParameter("passwordAgain");
        String propertyName = request.getParameter("propertyName");
        String street = request.getParameter("streetAddress");
        String city = request.getParameter("city");
        int isCommercial = request.getParameter("isCommercial").equals("yes")? 1 : 0;
        int isPublic = request.getParameter("isPublic").equals("yes")? 1 : 0;
        String propertyType = request.getParameter("propertyType");
        int zip = Integer.parseInt(request.getParameter("zip"));
        double acres = Double.parseDouble(request.getParameter("acres"));
        if (!password.equals(passwordAgain)){
            request.getSession().setAttribute("OwnerRegfail", 3);
            request.getRequestDispatcher("/ownerRegister.jsp").forward(request, response);
        } else if (password.length() < 8){
            request.getSession().setAttribute("OwnerRegfail", 4);
            request.getRequestDispatcher("/ownerRegister.jsp").forward(request, response);
        }else {
            int line = DBConnectionUtil.update("insert into User values(\""+username+"\",\""+email+"\",\""+MD5Util.crypt(password)+"\",\""+"OWNER"+"\")");
            if (line == 0){
                request.getSession().setAttribute("OwnerRegfail", 1);
                request.getRequestDispatcher("/ownerRegister.jsp").forward(request, response);
            }else {
                int line2 = DBConnectionUtil.update("insert into Property values(null,\""+propertyName+"\","+acres+","+isCommercial+","+isPublic+",\""+street+"\",\""+city+"\","+zip+",\""+propertyType+"\",\""+username+"\", null)");
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
                System.out.println("insert into Has values("+propertyID.toString()+",\""+request.getParameter("cropSelect")+"\")");
                if( propertyType.equals("FARM")){
                    DBConnectionUtil.update("insert into Has values("+propertyID.toString()+",\""+request.getParameter("animalSelect")+"\")");
                }
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
