package tools;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.Dbdao;
import pub.PublicFile;
import pri.PrivateFile;

public class IndexProcess {
    public static void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Dbdao dd = new Dbdao();
            HttpSession session = request.getSession();
            try {
                String sql = "SELECT pub.id, pub.public_to_private, pri.file_name, user.user_name, pri.size"
                + " FROM public_files pub, private_files pri, user "
                + " WHERE pub.public_to_private = pri.id AND pri.file_to_user = user.id;";
                ResultSet rspub = dd.query(sql);
                List<PublicFile> lpub = new ArrayList<PublicFile>();
            
                while(rspub.next()) {
                    int id = rspub.getInt(1);
                    int pubToPri = rspub.getInt(2);
                    String fileName = rspub.getString(3);
                    String owner = rspub.getString(4);
                    double size = rspub.getDouble(5);
                    PublicFile pubFile = new PublicFile(id, pubToPri, fileName, owner, size);
                    lpub.add(pubFile);
                }
                request.setAttribute("pubfiles", lpub);
                rspub.close();
                
            } catch(SQLException e) {
                e.printStackTrace();
            }

            if(session.getAttribute("username") != null) {
                try {
                    String sql = "SELECT pri.id, pri.file_to_user, pri.file_name, user.user_name, pri.size "
                                + " FROM private_files pri, user WHERE pri.file_to_user = user.id;";
                    List<PrivateFile> lpri = new ArrayList<PrivateFile>();
                    ResultSet rspri = dd.query(sql);
                    while(rspri.next()) {
                        int id = rspri.getInt(1);
                        int fileToUser = rspri.getInt(2);
                        String fileName = rspri.getString(3);
                        String owner = rspri.getString(4);
                        double size = rspri.getDouble(5);
                        PrivateFile priFile = new PrivateFile(id, fileToUser, fileName, owner, size);
                        lpri.add(priFile);
                    }
                    request.setAttribute("prifiles", lpri);
                    rspri.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }    
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}