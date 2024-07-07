package org.example;

import org.example.connection.DBConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private final static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("Program Streated..");
        DBConnection db_connection = new DBConnection();
        db_connection.getConnection();

        logger.info("Call the Service Class..");
        UserApp userApp = new UserApp();
        userApp.start();
    }
}