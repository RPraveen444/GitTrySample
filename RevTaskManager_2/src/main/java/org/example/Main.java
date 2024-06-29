package org.example;

import org.example.connection.DBConnection;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        DBConnection db_connection = new DBConnection();
        db_connection.getConnection();

        UserApp userApp = new UserApp();
        userApp.start();

    }
}