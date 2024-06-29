package org.example.dao;

import org.example.connection.DBConnection;
import org.example.models.Customers;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO implements BaseDAO<Customers> {

    Connection conn;
    public CustomerDAO(){
        conn = DBConnection.getConnection();
    }


    @Override
    public Customers create(Customers customers) {
        String sql = "INSERT INTO customers(CustomerID, CustomerName, ContactName, Address, City, PostalCode, Country) VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,customers.getCustomer_id());
            pst.setString(2,customers.getCustomer_name());
            pst.setString(3,customers.getContact_name());
            pst.setString(4,customers.getAddress());
            pst.setString(5,customers.getCity());
            pst.setString(6,customers.getPostal_code());
            pst.setString(7,customers.getCountry());
            int rows = pst.executeUpdate();
            System.out.println(rows);
            return customers;
        } catch(SQLException ex){
            System.out.println(ex.getMessage());

        }
        return null;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM customers WHERE CustomerID = ?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,id);
            int rows = pst.executeUpdate();
            return true;
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public Customers update(Customers customers) {
        return null;
    }

    @Override
    public Customers getId(int id) {
        return null;
    }

    @Override
    public List<Customers> getAll() {
        try {
            Statement statement = conn.createStatement();
            String sql = "SELECT * FROM customers";
            ResultSet rs = statement.executeQuery(sql);
            List<Customers> customers = new ArrayList<>();
            while(rs.next()){
                Customers customer = new Customers();
                customer.setCustomer_id(rs.getInt("CustomerID"));
                customer.setCustomer_name(rs.getString("CustomerName"));
                customer.setContact_name(rs.getString("ContactName"));
                customer.setAddress(rs.getString("Address"));
                customer.setCity(rs.getString("City"));
                customer.setPostal_code(rs.getString("PostalCode"));
                customer.setCountry(rs.getString("Country"));
                customers.add(customer);
            }
            return customers;
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }
}