package user;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;

import database.Dbdao;

public class User {
    private int id = -1;
    private String userName;
    private String password;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public boolean isExist() throws SQLException, ClassNotFoundException {
        Dbdao dd = new Dbdao();
        ResultSet results = dd.query("SELECT user_name, password FROM user WHERE user_name = '" + userName +"';");
        while(results.next()) {
            String password = results.getString(2);
            if(password.equals(this.password)) {
                results.close();
                dd.closeConn();
                return true;
            }
        }
        results.close();
        dd.closeConn();
        return false;
    }

}