package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@javax.servlet.annotation.WebServlet(name = "InitServlet", loadOnStartup = 2, urlPatterns = "")
public class InitServlet extends javax.servlet.http.HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        response.setStatus(response.SC_MOVED_PERMANENTLY);
        response.setHeader("Location", "login.jsp");
    }
}
