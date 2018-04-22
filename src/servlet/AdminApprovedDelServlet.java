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

@WebServlet(name = "AdminApprovedDelServlet", urlPatterns = "/AdminApprovedDelServlet")
public class AdminApprovedDelServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int index = Integer.parseInt(request.getParameter("rowIndexDL"));
//        System.out.println(index);
        List<FarmItem> approvedItems = (List<FarmItem>) request.getSession().getAttribute("approvedItems");
        if (index < 0 || index >= approvedItems.size()) {
            request.getRequestDispatcher("/approvedItems.jsp").forward(request, response);
        }
        String selected = approvedItems.get(index).getName();
        DBConnectionUtil.update("UPDATE FarmItem\n" +
                "SET IsApproved = 0\n" +
                "WHERE Name = \"" + selected + "\"");
        List<FarmItem> approvedItems_update = UserService.getapprovedItems();
        request.getSession().setAttribute("approvedItems", approvedItems_update);
        request.getRequestDispatcher("/approvedItems.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
