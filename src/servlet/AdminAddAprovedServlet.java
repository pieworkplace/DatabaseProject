package servlet;

import database.JDBC.DBConnectionUtil;
import database.classes.FarmItem;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminAddAprovedServlet", urlPatterns = "/AdminAddAprovedServlet")
public class AdminAddAprovedServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newItem = request.getParameter("ItemNameText");
        String ItemType = request.getParameter("ItemTypeText");
        DBConnectionUtil.update("INSERT INTO FarmItem(Name, IsApproved, Type) VALUES ( \"" + newItem + "\" ,1, \"" + ItemType +"\");");
        List<FarmItem> approvedItems_update = UserService.getapprovedItems();
        request.getSession().setAttribute("approvedItems", approvedItems_update);
        request.getRequestDispatcher("/approvedItems.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
