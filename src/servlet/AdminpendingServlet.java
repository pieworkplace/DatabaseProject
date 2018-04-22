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

@WebServlet(name = "AdminpendingServlet", urlPatterns = "/AdminpendingServlet")
public class AdminpendingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int index = Integer.parseInt(request.getParameter("rowIndexAP"));
//        System.out.println(index);
        List<FarmItem> pendingItems = (List<FarmItem>) request.getSession().getAttribute("pendingItems");
        if (index < 0 || index >= pendingItems.size()) {
            request.getRequestDispatcher("/pendingItems.jsp").forward(request, response);
        }
        String selected = pendingItems.get(index).getName();
        DBConnectionUtil.update("UPDATE FarmItem\n" +
                "SET IsApproved = 1\n" +
                "WHERE Name = \"" + selected + "\"");
        List<FarmItem> pendingItems_update = UserService.getpendingItems();
        request.getSession().setAttribute("pendingItems", pendingItems_update);
        request.getRequestDispatcher("/pendingItems.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
