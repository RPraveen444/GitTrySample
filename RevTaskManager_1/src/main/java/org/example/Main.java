package org.example;

import org.example.connection.DBConnection;

public class Main {
    public static void main(String[] args) {
        DBConnection db_connection = new DBConnection();
        db_connection.getConnection();

//        UserApp userApp = new UserApp();
//        userApp.start();
    }
}