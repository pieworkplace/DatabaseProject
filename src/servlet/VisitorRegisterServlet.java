package servlet;

import database.JDBC.DBConnectionUtil;
import database.JDBC.MD5Util;
import database.JDBC.SQLPreparator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "VisitorRegisterServlet", urlPatterns = "/VisitorRegisterServlet")
public class VisitorRegisterServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (
                request.getParameter("email") == null ||
                request.getParameter("username") == null ||
                request.getParameter("password") == null ||
                request.getParameter("passwordAgain") == null){
            return;
        }else {
            final String email = request.getParameter("email");
            final String username = request.getParameter("username");
            final String password = request.getParameter("password");
            final String passwordAgain = request.getParameter("passwordAgain");
            if (!password.equals(passwordAgain)){
                request.getSession().setAttribute("VisitorRegfail", 3);
                request.getRequestDispatcher("/visitorRegister.jsp").forward(request, response);
            } else if (password.length() < 8){
                request.getSession().setAttribute("VisitorRegfail", 4);
                request.getRequestDispatcher("/visitorRegister.jsp").forward(request, response);
            }else {
//                int line = DBConnectionUtil.preUpdate("insert into User values(?,?,?,\"VISITOR\")", new SQLPreparator() {
//                    @Override
//                    public void prepareSQL(PreparedStatement preparedStatement) throws SQLException {
//                        preparedStatement.setString(1, username);
//                        preparedStatement.setString(2, email);
//                        preparedStatement.setString(3, MD5Util.crypt(password));
//                    }
//                });
                int line = DBConnectionUtil.update("insert into User values(\""+username+"\",\""+email+"\",\""+MD5Util.crypt(password)+"\",\""+"VISITOR"+"\")");
                if (line == 0){
                    request.getSession().setAttribute("VisitorRegfail", 1);
                    request.getRequestDispatcher("/visitorRegister.jsp").forward(request, response);
                }else {
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                }
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
