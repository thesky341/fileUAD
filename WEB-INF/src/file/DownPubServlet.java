package file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.*;

@WebServlet(name = "downpub", urlPatterns = {"/down/pub/*"})
public class DownPubServlet extends HttpServlet {
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            HttpSession session = request.getSession();
            Dbdao dd = new Dbdao();
            String path = request.getPathInfo();
            if(path.length() != 1) {
                String id = path.substring(1);
                ResultSet rs = dd.query("SELECT pri.file_name, user.user_name FROM private_files pri, public_files pub, user WHERE pub.public_to_private = pri.id AND pri.file_to_user = user.id AND pub.id = " + id + ";");
                if(rs.next()) {
                    String serverFileName = rs.getString(1);
                    String fileName = serverFileName.substring(0, serverFileName.indexOf(".suf."));
                    String userName = rs.getString(2);
                    File file = new File(request.getServletContext().getRealPath("/WEB-INF/files/" + userName + "/" + serverFileName));
                    if(file.exists()) {
                        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
                        try (
                            FileInputStream fileInput = new FileInputStream(file);
                            ServletOutputStream out = response.getOutputStream();
                        ) {
                            byte[] buff = new byte[1024];
                            int len;
                            while((len = fileInput.read(buff)) != -1) {
                                out.write(buff, 0, len);
                            }
                        }
                    } 
                }
            }
            response.sendRedirect("/");
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}