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

@WebServlet(name = "sharepri", urlPatterns = {"/share/pri/*"})
public class SharePriServlet extends HttpServlet {
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException{
        try {
            Dbdao dd = new Dbdao();
            HttpSession session = request.getSession();
            String path = request.getPathInfo();
            if(path.length() != 1) {
                String id = path.substring(1);
                ResultSet rs = dd.query("SELECT pri.file_name, user.user_name FROM private_files pri, user WHERE pri.file_to_user = user.id && pri.id = " + id + ";");
                if(rs.next()) {
                    String serverFileName = rs.getString(1);
                    String fileName = serverFileName.substring(0, serverFileName.indexOf(".suf."));
                    String userName = rs.getString(2);
                    if(session.getAttribute("username") != null && ((String)session.getAttribute("username")).equals(userName)) {
                        File file = new File(request.getServletContext().getRealPath("/WEB-INF/files/" + userName + "/" + serverFileName));
                        if(file.exists()) {
                            ResultSet rspub = dd.query("SELECT * FROM public_files WHERE public_to_private = " + id + ";");
                            if(!rspub.next()) {
                                dd.update("INSERT INTO public_files (public_to_private) VALUES (" + id + ");");
                            }
                        } else {
                            dd.update("DELETE FROM private_files WHERE id = " + id + ";");
                        }
                    } 
                }
            }
            response.sendRedirect("/pri");
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}