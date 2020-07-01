package file;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.*;

@WebServlet(name = "delpub", urlPatterns = {"/del/pub/*"})
public class DelPubServlet extends HttpServlet {
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException{
        try {
            Dbdao dd = new Dbdao();
            HttpSession session = request.getSession();
            String path = request.getPathInfo();
            if(path.length() != 1) {
                String id = path.substring(1);
                ResultSet rs = dd.query("SELECT pri.file_name, user.user_name FROM private_files pri, public_files pub, user WHERE pub.public_to_private = pri.id AND pri.file_to_user = user.id AND pub.id = " + id + ";");
                if(rs.next()) {
                    String userName = rs.getString(2);
                    if(session.getAttribute("username") != null && ((String)session.getAttribute("username")).equals(userName)) {
                        dd.update("DELETE FROM public_files WHERE id = " + id + ";");
                    }
                }
            }
            response.sendRedirect("/");
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}