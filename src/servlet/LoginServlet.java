package servlet;

import database.classes.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User user = UserService.login(email, password);
        if (user != null){
            req.getSession().setAttribute("user", user);
            if (user.getUserType() == User.UserType.OWNER){
                req.getRequestDispatcher("/ownerCenter.jsp").forward(req, resp);
            }else if (user.getUserType() == User.UserType.ADMIN){
                req.getRequestDispatcher("/adminFunctionality.jsp").forward(req, resp);
            }else {
                req.getRequestDispatcher("/visitorCenter.jsp").forward(req, resp);
            }
        } else {
            req.getSession().setAttribute("fail", true);
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }

}
