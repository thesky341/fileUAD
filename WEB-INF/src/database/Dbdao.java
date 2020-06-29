package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Dbdao {
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://127.0.0.1:3306/fileUAD?useSSL=true";
    private String user = "root";
    private String password = "abcd1234";
    private Connection conn;
    private Statement statement;

    public Dbdao() throws SQLException {
        try {
            Class.forName(driver);
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
        conn = DriverManager.getConnection(url, user, password);
        statement = conn.createStatement();
    }

    public ResultSet query(String sql) throws SQLException {
        return statement.executeQuery(sql);
    }

    public int update(String sql) throws SQLException {
        return statement.executeUpdate(sql);
    }

    public void closeConn() throws SQLException {
        statement.close();
        conn.close();
    }
}