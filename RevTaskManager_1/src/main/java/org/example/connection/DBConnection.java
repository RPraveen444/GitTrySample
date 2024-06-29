package org.example.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection getConnection(){
        String url = "jdbc:mysql://localhost:3306/taskmanager";
        String user ="root";
        String password = "praveen444";

        try{
            System.out.println("connected2");
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("connected1");
            Connection conn = DriverManager.getConnection(url,user,password);
            System.out.println("connected");
            return conn;
        }catch(ClassNotFoundException | SQLException ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }
}