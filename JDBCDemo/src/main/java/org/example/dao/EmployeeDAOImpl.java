package org.example.dao;

import org.example.connection.DBConnection;
import org.example.model.Employees;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements  EmployeeDAO{

    Connection conn;

    public EmployeeDAOImpl(){
        this.conn = DBConnection.getConnection();
    }


    @Override
    public Employees create(Employees employee) {
        String sql = "INSERT INTO Employees(EmployeeId,FirstName,LastName,BirthDate,Photo,Notes) VALUES (?,?,?,?,?,?);";
        try{
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,employee.getEmployee_id());
            pst.setString(2,employee.getFirst_name());
            pst.setString(3,employee.getLast_name());
            pst.setString(4,employee.getBirth_date());
            pst.setString(5,employee.getPhoto());
            pst.setString(6,employee.getNotes());
            int rows = pst.executeUpdate();
            System.out.println(rows);
            return employee;
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM employees WHERE EmployeeID = ?;";
        try{
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,id);
            int rows_affected = pst.executeUpdate();
            return true;
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public String update(String s1,int num) {
        String sql = "UPDATE employees SET FirstName = ? WHERE EmployeeID = ?;";
        try{
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1,s1);
            pst.setInt(2,num);
            int rows_affected = pst.executeUpdate();
            return "updated";
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return "not created";
    }

    @Override
    public Object getId(String id) {
        return null;
    }

    @Override
    public List<Employees> getAll() {
        try{
            Statement statement  = conn.createStatement();
            String sql = "SELECT * FROM employees ORDER BY BirthDate ASC;";
            ResultSet rs=statement.executeQuery(sql);
            List<Employees> employees = new ArrayList<>();
            while(rs.next()){
                Employees employee = new Employees();

                employee.setEmployee_id(rs.getInt("EmployeeID"));
                employee.setLast_name(rs.getString("LastName"));
                employee.setFirst_name(rs.getString("FirstName"));
                employee.setBirth_date(rs.getString("BirthDate"));
                employee.setPhoto(rs.getString("Photo"));
                employee.setNotes(rs.getString("Notes"));

                employees.add(employee);
            }
//            System.out.println(employees);
            return employees;
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }


}
