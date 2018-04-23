package servlet;

import database.JDBC.DBConnectionUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "OwnerRequestApprovalServlet", urlPatterns = "/OwnerRequestApprovalServlet")
public class OwnerRequestApprovalServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String SearchItem = request.getParameter("SearchText");
        String SearchType = request.getParameter("SearchTypeText");
        int lines = DBConnectionUtil.update("INSERT INTO FarmItem(Name, IsApproved, Type) VALUES (\""+ SearchItem +"\", 0, \""+ SearchType +"\");");
        if (lines != 0){
            request.getRequestDispatcher("/manageProperties.jsp").forward(request, response);
        }
        else {
            request.getSession().setAttribute("RequestFail", true);
            request.getRequestDispatcher("/manageProperties.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
