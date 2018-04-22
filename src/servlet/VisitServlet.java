package servlet;

import database.classes.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;

@WebServlet(name = "VisitServlet", urlPatterns = "/VisitServlet")
public class VisitServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int rating = Integer.parseInt(request.getParameter("rate"));
        int id = Integer.parseInt(request.getParameter("id"));

        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss aa");
        String time = dateFormat.format(now);

        String userName = ((User) request.getSession().getAttribute("user")).getUsername();
        UserService.addVisit(userName, id, time, rating);
        request.getRequestDispatcher("/VisitorManageServlet").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
