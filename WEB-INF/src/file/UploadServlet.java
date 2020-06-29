import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import database.*;

@WebServlet(name = "upload", urlPatterns = {"/file/upload"})
@MultipartConfig
public class UploadServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            Dbdao dd = new Dbdao();
            HttpSession session = request.getSession();
            response.setContentType("text/html;charset=utf-8");
            request.setCharacterEncoding("utf-8");
            Part part = request.getPart("file");
            String userName = (String)session.getAttribute("username");
            File dir = new File(request.getServletContext().getRealPath("/WEB-INF/files/") + userName + "/");
            if(!dir.exists()) {
                dir.mkdirs();
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}