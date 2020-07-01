import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

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
            File dir = new File(request.getServletContext().getRealPath("/WEB-INF/files/") + userName);
            if(!dir.exists()) {
                dir.mkdirs();
            }
            String fileNameInfo = part.getHeader("content-disposition");
            String fileName = fileNameInfo.substring(fileNameInfo.indexOf("filename=\"") + 10, fileNameInfo.length() - 1);
            String serverFileName = fileName + ".suf." + new Date();
            ResultSet rs =  dd.query("SELECT id FROM user where user_name = '" + userName + "';");
            if(rs.next()) {
                int userId = rs.getInt(1);
                double size = part.getSize() * 1.0 / 1024.0 /1024.0;
                System.out.println(serverFileName);
                part.write(dir + "/" + serverFileName);
                String sql = "INSERT INTO private_files (file_name, file_to_user, size) VALUES ( '" 
                    + serverFileName + "', " + userId + " , " + size + " );";
                dd.update(sql);
            }
            response.sendRedirect("/pri");
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}