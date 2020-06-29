package user;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "logout", urlPatterns = {"/user/logout"})
public class LogoutServlet extends HttpServlet {
    public void service(HttpServletRequest request, HttpServletResponse response)
        throws IOException {
        HttpSession session = request.getSession(false);
        if(session != null && session.getAttribute("username") != null) {
            session.removeAttribute("username");
        }
        response.sendRedirect("/");
    }
}