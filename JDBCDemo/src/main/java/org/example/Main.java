package org.example;

import org.example.connection.DBConnection;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        DBConnection db_connection = new DBConnection();
        db_connection.getConnection();

        //SELECT QUERRY
        EmployeeApp emp = new EmployeeApp();
        emp.getEmployeeName();

        //UPDATE QUERRY
//        System.out.println(emp.updateEmp());

        //INSERT QUERRY
//        emp.createEmployee();

        //DELETE QUERRY
//        emp.deleteEmployee();

    }
}