package user;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;


@WebServlet(name = "loginprocess", urlPatterns = {"/user/loginprocess"})
public class LoginProcess extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws UnsupportedEncodingException, ServletException, IOException {
        String errMsg = "";
        request.setCharacterEncoding("utf-8");
        String userName = (String)request.getParameter("user");
        String password = (String)request.getParameter("password");
        HttpSession session = request.getSession(true);
        try {
            // System.out.println(userName);
            // System.out.println(password);
            User user = new User(userName, password);
            if(user.isExist()) {
                session.setAttribute("username", userName);
                response.sendRedirect("/");         
            } else {
                errMsg = "用户名或者密码错误";
                request.setAttribute("errMsg", errMsg);
                request.setAttribute("tip", "show");
                RequestDispatcher rd = request.getRequestDispatcher("/jsp/login.jsp");
                rd.forward(request, response);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}